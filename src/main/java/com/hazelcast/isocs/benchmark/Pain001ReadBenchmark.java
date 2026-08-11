package com.hazelcast.isocs.benchmark;

import com.hazelcast.simulator.test.annotations.TimeStep;
import com.hazelcast.simulator.test.annotations.Verify;

/** Retrieves each complete pain.001 POJO from a pre-seeded map; no timed writes run concurrently. */
public final class Pain001ReadBenchmark extends AbstractPain001Benchmark {
    @Override
    protected boolean seedMapBeforeRun() {
        return true;
    }

    @Override
    protected void initialiseProbes() {
        initialiseReadProbes();
    }

    @TimeStep
    public void readPojo() {
        readOnce();
    }

    @Verify(global = false)
    public void verify() {
        verifyReads();
    }
}
