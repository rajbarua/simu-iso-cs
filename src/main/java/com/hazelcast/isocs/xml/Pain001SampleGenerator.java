package com.hazelcast.isocs.xml;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/**
 * Creates a large, JAXB-readable pain.001.001.03 sample without first building the object graph in memory.
 * Filler is stored in Ustrd fields so it remains present in the deserialized POJO.
 */
public final class Pain001SampleGenerator {
    public static final int DEFAULT_TRANSACTIONS = 150_000;
    public static final int DEFAULT_PAYMENT_INFOS = 2_000;
    public static final long DEFAULT_TARGET_BYTES = 180L * 1024 * 1024;
    public static final Path DEFAULT_OUTPUT = Path.of(
            "src/main/resources/samples/pain001-150k-2k-180MiB.xml.gz");

    private static final int MAX_USTRD_LENGTH = 140;
    private static final String RMT_START = "        <RmtInf>\n";
    private static final String RMT_END = "        </RmtInf>\n";
    private static final String USTRD_PREFIX = "          <Ustrd>";
    private static final String USTRD_SUFFIX = "</Ustrd>\n";
    private static final int REMITTANCE_FIXED_BYTES = asciiLength(RMT_START) + asciiLength(RMT_END);
    private static final int USTRD_WRAPPER_BYTES = asciiLength(USTRD_PREFIX) + asciiLength(USTRD_SUFFIX);
    private static final String FILLER = "X".repeat(MAX_USTRD_LENGTH);

    private Pain001SampleGenerator() {
    }

    public static void main(String[] args) throws Exception {
        Map<String, String> options = parseOptions(args);
        int transactions = parsePositiveInt(options, "transactions", DEFAULT_TRANSACTIONS);
        int paymentInfos = parsePositiveInt(options, "payment-infos", DEFAULT_PAYMENT_INFOS);
        long targetMiB = parsePositiveLong(options, "target-mib", DEFAULT_TARGET_BYTES / (1024 * 1024));
        Path output = Path.of(options.getOrDefault("output", DEFAULT_OUTPUT.toString()));

        GenerationResult result = generate(output, transactions, paymentInfos, targetMiB * 1024 * 1024);
        System.out.printf(
                "Generated %s: %,d bytes uncompressed, %,d bytes on disk, %,d transactions, %,d payment infos, "
                        + "%,d or %,d filler chars per transaction%n",
                output.toAbsolutePath(), result.uncompressedBytes(), result.compressedBytes(), transactions,
                paymentInfos, result.baseFillerChars(), result.baseFillerChars() + (result.upgradedTransactions() > 0 ? 1 : 0));
    }

    public static GenerationResult generate(Path output, int transactions, int paymentInfos, long targetBytes)
            throws IOException {
        validate(transactions, paymentInfos, targetBytes);
        Path parent = output.toAbsolutePath().getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }

        long baseBytes = render(OutputStream.nullOutputStream(), false, transactions, paymentInfos, 0, 0, 0);
        long desiredFillerBytes = Math.max(0L, targetBytes - baseBytes);
        int baseFillerChars = largestUniformFiller(transactions, desiredFillerBytes);
        long uniformFillerBytes = (long) transactions * remittanceBytes(baseFillerChars);
        long remainder = desiredFillerBytes - uniformFillerBytes;

        long increment = remittanceBytes(baseFillerChars + 1) - remittanceBytes(baseFillerChars);
        int upgradedTransactions = (int) Math.min(transactions, remainder / increment);
        remainder -= (long) upgradedTransactions * increment;

        int commentOverhead = asciiLength("    <!---->\n");
        int trailingPaddingChars = remainder >= commentOverhead ? Math.toIntExact(remainder - commentOverhead) : 0;

        boolean gzip = output.toString().endsWith(".gz");
        long uncompressedBytes;
        try (OutputStream file = Files.newOutputStream(output);
             OutputStream encoded = gzip ? new GZIPOutputStream(file, 1 << 20) : file) {
            uncompressedBytes = render(encoded, false, transactions, paymentInfos, baseFillerChars,
                    upgradedTransactions, trailingPaddingChars);
        }
        return new GenerationResult(uncompressedBytes, Files.size(output), baseFillerChars, upgradedTransactions);
    }

    private static long render(OutputStream output, boolean closeOutput, int transactions, int paymentInfos,
                               int baseFillerChars, int upgradedTransactions, int trailingPaddingChars)
            throws IOException {
        CountingOutputStream counting = new CountingOutputStream(output);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(counting, StandardCharsets.UTF_8), 1 << 20);
        writeHeader(writer, transactions);

        int baseTransactionsPerPaymentInfo = transactions / paymentInfos;
        int transactionRemainder = transactions % paymentInfos;
        int globalTransaction = 0;
        for (int paymentInfo = 0; paymentInfo < paymentInfos; paymentInfo++) {
            int count = baseTransactionsPerPaymentInfo + (paymentInfo < transactionRemainder ? 1 : 0);
            writePaymentInfoStart(writer, paymentInfo, count);
            for (int localTransaction = 0; localTransaction < count; localTransaction++) {
                int fillerChars = baseFillerChars + (globalTransaction < upgradedTransactions ? 1 : 0);
                writeTransaction(writer, globalTransaction, fillerChars);
                globalTransaction++;
            }
            writer.write("    </PmtInf>\n");
        }

        if (trailingPaddingChars > 0) {
            writer.write("    <!--");
            writeRepeated(writer, 'P', trailingPaddingChars);
            writer.write("-->\n");
        }
        writer.write("  </CstmrCdtTrfInitn>\n</Document>\n");
        writer.flush();
        if (closeOutput) {
            writer.close();
        }
        return counting.count();
    }

    private static void writeHeader(BufferedWriter writer, int transactions) throws IOException {
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        writer.write("<Document xmlns=\"urn:iso:std:iso:20022:tech:xsd:pain.001.001.03\">\n");
        writer.write("  <CstmrCdtTrfInitn>\n");
        writer.write("    <GrpHdr>\n");
        writer.write("      <MsgId>BENCH-PAIN001-150K</MsgId>\n");
        writer.write("      <CreDtTm>2026-08-07T00:00:00Z</CreDtTm>\n");
        writer.write("      <NbOfTxs>" + transactions + "</NbOfTxs>\n");
        writer.write("      <CtrlSum>150000.00</CtrlSum>\n");
        writer.write("      <InitgPty><Nm>Benchmark Sender</Nm></InitgPty>\n");
        writer.write("    </GrpHdr>\n");
    }

    private static void writePaymentInfoStart(BufferedWriter writer, int paymentInfo, int count) throws IOException {
        writer.write("    <PmtInf>\n");
        writer.write("      <PmtInfId>PMT" + zeroPadded(paymentInfo + 1, 4) + "</PmtInfId>\n");
        writer.write("      <PmtMtd>TRF</PmtMtd>\n");
        writer.write("      <BtchBookg>true</BtchBookg>\n");
        writer.write("      <NbOfTxs>" + count + "</NbOfTxs>\n");
        writer.write("      <CtrlSum>" + count + ".00</CtrlSum>\n");
        writer.write("      <PmtTpInf><SvcLvl><Cd>SEPA</Cd></SvcLvl></PmtTpInf>\n");
        // The JAXB binding maps xs:date to OffsetDateTime, so this intentionally carries an offset.
        writer.write("      <ReqdExctnDt>2026-08-08T00:00:00Z</ReqdExctnDt>\n");
        writer.write("      <Dbtr><Nm>Benchmark Sender</Nm></Dbtr>\n");
        writer.write("      <DbtrAcct><Id><IBAN>ZZ00SENDER000000000000000001</IBAN></Id></DbtrAcct>\n");
        writer.write("      <DbtrAgt><FinInstnId><BIC>SENDZZZZ</BIC></FinInstnId></DbtrAgt>\n");
    }

    private static void writeTransaction(BufferedWriter writer, int transaction, int fillerChars) throws IOException {
        String id = zeroPadded(transaction + 1, 6);
        writer.write("      <CdtTrfTxInf>\n");
        writer.write("        <PmtId><EndToEndId>TX" + id + "</EndToEndId></PmtId>\n");
        writer.write("        <Amt><InstdAmt Ccy=\"EUR\">1.00</InstdAmt></Amt>\n");
        writer.write("        <CdtrAgt><FinInstnId><BIC>RECVZZZZ</BIC></FinInstnId></CdtrAgt>\n");
        writer.write("        <Cdtr><Nm>Receiver " + id + "</Nm></Cdtr>\n");
        writer.write("        <CdtrAcct><Id><IBAN>ZZ00RECEIVER0000000000000001</IBAN></Id></CdtrAcct>\n");
        if (fillerChars > 0) {
            writer.write(RMT_START);
            int remaining = fillerChars;
            while (remaining > 0) {
                int chunk = Math.min(remaining, MAX_USTRD_LENGTH);
                writer.write(USTRD_PREFIX);
                writer.write(FILLER, 0, chunk);
                writer.write(USTRD_SUFFIX);
                remaining -= chunk;
            }
            writer.write(RMT_END);
        }
        writer.write("      </CdtTrfTxInf>\n");
    }

    private static int largestUniformFiller(int transactions, long budget) {
        int low = 0;
        int high = 1;
        while ((long) transactions * remittanceBytes(high) <= budget) {
            high = Math.multiplyExact(high, 2);
        }
        while (low + 1 < high) {
            int middle = low + (high - low) / 2;
            if ((long) transactions * remittanceBytes(middle) <= budget) {
                low = middle;
            } else {
                high = middle;
            }
        }
        return low;
    }

    private static long remittanceBytes(int fillerChars) {
        if (fillerChars == 0) {
            return 0L;
        }
        int chunks = (fillerChars + MAX_USTRD_LENGTH - 1) / MAX_USTRD_LENGTH;
        return REMITTANCE_FIXED_BYTES + (long) chunks * USTRD_WRAPPER_BYTES + fillerChars;
    }

    private static void writeRepeated(BufferedWriter writer, char value, int count) throws IOException {
        char[] block = new char[Math.min(count, 8192)];
        java.util.Arrays.fill(block, value);
        int remaining = count;
        while (remaining > 0) {
            int length = Math.min(remaining, block.length);
            writer.write(block, 0, length);
            remaining -= length;
        }
    }

    private static String zeroPadded(int value, int width) {
        return String.format(java.util.Locale.ROOT, "%0" + width + "d", value);
    }

    private static int asciiLength(String value) {
        return value.getBytes(StandardCharsets.US_ASCII).length;
    }

    private static void validate(int transactions, int paymentInfos, long targetBytes) {
        if (transactions <= 0 || paymentInfos <= 0 || paymentInfos > transactions) {
            throw new IllegalArgumentException("Require transactions >= payment-infos > 0");
        }
        if (targetBytes <= 0 || targetBytes > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("target size must be between 1 byte and 2 GiB");
        }
    }

    private static Map<String, String> parseOptions(String[] args) {
        Map<String, String> options = new HashMap<>();
        for (int index = 0; index < args.length; index += 2) {
            if (!args[index].startsWith("--") || index + 1 >= args.length) {
                throw new IllegalArgumentException(
                        "Options must be --name value pairs: --transactions, --payment-infos, --target-mib, --output");
            }
            options.put(args[index].substring(2), args[index + 1]);
        }
        return options;
    }

    private static int parsePositiveInt(Map<String, String> options, String name, int defaultValue) {
        int value = Integer.parseInt(options.getOrDefault(name, Integer.toString(defaultValue)));
        if (value <= 0) {
            throw new IllegalArgumentException(name + " must be positive");
        }
        return value;
    }

    private static long parsePositiveLong(Map<String, String> options, String name, long defaultValue) {
        long value = Long.parseLong(options.getOrDefault(name, Long.toString(defaultValue)));
        if (value <= 0) {
            throw new IllegalArgumentException(name + " must be positive");
        }
        return value;
    }

    public record GenerationResult(long uncompressedBytes, long compressedBytes, int baseFillerChars,
                                   int upgradedTransactions) {
    }

    private static final class CountingOutputStream extends OutputStream {
        private final OutputStream delegate;
        private long count;

        private CountingOutputStream(OutputStream delegate) {
            this.delegate = delegate;
        }

        @Override
        public void write(int value) throws IOException {
            delegate.write(value);
            count++;
        }

        @Override
        public void write(byte[] bytes, int offset, int length) throws IOException {
            delegate.write(bytes, offset, length);
            count += length;
        }

        @Override
        public void flush() throws IOException {
            delegate.flush();
        }

        private long count() {
            return count;
        }
    }
}
