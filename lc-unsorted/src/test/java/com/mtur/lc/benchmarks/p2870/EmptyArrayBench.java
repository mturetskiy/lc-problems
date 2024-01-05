package com.mtur.lc.benchmarks.p2870;

import com.mtur.lc.EmptyArrayMinOps;
import com.mtur.lc.EmptyArrayMinOpsOpt;
import com.mtur.lc.EmptyArrayMinOpsOpt2;
import com.mtur.lc.utils.DataGenerator;
import com.mtur.lc.utils.JmhUtils;
import org.openjdk.jmh.annotations.*;

public class EmptyArrayBench {
    @Benchmark
    public void emptyArray(BenchState state) {
        new EmptyArrayMinOps().minOperations(state.ints);
    }

    @Benchmark
    public void emptyArrayOpt(BenchState state) {
        new EmptyArrayMinOpsOpt().minOperations(state.ints);
    }

    @Benchmark
    public void emptyArrayOpt2(BenchState state) {
        new EmptyArrayMinOpsOpt2().minOperations(state.ints);
    }

    @State(value = Scope.Thread)
    public static class BenchState {
        int[] ints;

        @Param({"10000", "100000"})
        int rowColCount;

        @Setup(Level.Trial)
        public void init() {
            ints = DataGenerator.generateUnsortedArray(rowColCount, 960000, 1_000_000);
        }
    }

    public static void main(String[] args) {
        JmhUtils.runJmh();
    }
}