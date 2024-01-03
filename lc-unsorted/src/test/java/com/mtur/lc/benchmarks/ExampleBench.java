package com.mtur.lc.benchmarks;

import com.mtur.lc.utils.JmhUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ExampleBench {
    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    @Measurement(iterations = 3)
    @Warmup(iterations = 2)
    @OutputTimeUnit(TimeUnit.MILLISECONDS)
    @Fork(value = 1)
    public void logic(BenchState state) {
        String res = "";
        for (int i = 0; i < state.count; i++) {
            res = res + state.data.charAt(i);
        }

        log.info("Running with data: {}", state.data);
    }

    @State(value = Scope.Thread)
    public static class BenchState {
        @Param({"5000", "10000", "30000"})
        int count;

        String data;

        @Setup(Level.Trial)
        public void init() {
            data = RandomStringUtils.random(30000);
        }
    }

    public static void main(String[] args) {
        JmhUtils.runJmhNoDefaults();
    }
}
