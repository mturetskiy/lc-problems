package com.mtur.lc.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TestUtils {
    public static final long CODE_RUN_DELAY_MS = 3000;

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            log.info("{}", row);
        }
    }

    public static <T> void printMatrix(List<List<T>> matrix) {
        for (List<T> row : matrix) {
            log.info("{}", row);
        }
    }

    public static void printRunTime(String name, long startTimeNano) {
        log.info("[{}] Done in: {}", name, formatDuration(System.nanoTime() - startTimeNano));
    }

    public static void measureDuration(String name, Runnable code) throws Exception {
        System.gc();
        Thread.sleep(CODE_RUN_DELAY_MS);
        code.run();

        long start = System.nanoTime();
        code.run();

        printRunTime(name, start);
    }

    public static String formatDuration(long durationNs) {
        if (durationNs < 1_000) {
            return durationNs + " ns";
        }

        if (durationNs < 1_000_000) {
            return durationNs / 1_000.0f + " mks";
        }

        if (durationNs < 1_000_000_000) {
            return durationNs / 1_000_000.0f + " ms";
        }

        return durationNs / 1_000_000_000.0f + " s";
    }
}
