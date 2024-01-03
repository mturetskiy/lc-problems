package com.mtur.lc.benchmarks;

import com.mtur.lc.LaserBeams;
import com.mtur.lc.LaserBeamsOpt;
import com.mtur.lc.LaserBeamsOpt2;
import com.mtur.lc.utils.JmhUtils;
import org.openjdk.jmh.annotations.*;

import static com.mtur.lc.utils.DataGenerator.generateStrMatrix;

public class LaserBeamsBench {
    @Benchmark
    public void laserBeams(BenchState state) {
        new LaserBeams().numberOfBeams(state.matrix);
    }

    @Benchmark
    public void laserBeamsOpt(BenchState state) {
        new LaserBeamsOpt().numberOfBeams(state.matrix);
    }

    @Benchmark
    public void laserBeamsOpt2(BenchState state) {
        new LaserBeamsOpt2().numberOfBeams(state.matrix);
    }

    @State(value = Scope.Thread)
    public static class BenchState {
        String[] matrix;

        @Param({"5000", "10000", "30000"})
        int rowColCount;

        @Setup(Level.Trial)
        public void init() {
            matrix = generateStrMatrix(rowColCount, rowColCount, '0', '1');
        }
    }

    public static void main(String[] args) {
        JmhUtils.runJmh();
    }
}