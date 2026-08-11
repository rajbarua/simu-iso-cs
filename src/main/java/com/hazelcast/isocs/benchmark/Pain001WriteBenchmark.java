package com.hazelcast.isocs.benchmark;

import com.hazelcast.simulator.test.annotations.TimeStep;
import com.hazelcast.simulator.test.annotations.Verify;

/** Parses XML and stores each complete pain.001 POJO; no timed reads run concurrently. */
public final class Pain001WriteBenchmark extends AbstractPain001Benchmark {
    @Override
    protected boolean seedMapBeforeRun() {
        return false;
    }

    @Override
    protected void initialiseProbes() {
        initialiseWriteProbes();
    }

    @TimeStep
    public void parseAndSet() {
        writeOnce();
    }

    @Verify(global = false)
    public void verify() {
        verifyWrites();
    }
}
