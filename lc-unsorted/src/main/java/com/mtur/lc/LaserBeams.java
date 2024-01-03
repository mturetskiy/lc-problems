package com.mtur.lc;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;

import java.io.IOException;

public class LaserBeams {

    public int numberOfBeams(String[] bank) {
        int beamsCnt = 0;
        int prevRowLasersCnt = 0;

        for (String row : bank) {
            int currRowLasersCnt = 0;
            for (int i = 0; i < row.length(); i++) {
                if (row.charAt(i) == '1') {
                    currRowLasersCnt++;
                }
            }

            if (currRowLasersCnt > 0) {
                if (prevRowLasersCnt > 0) {
                    beamsCnt += currRowLasersCnt * prevRowLasersCnt;
                }

                prevRowLasersCnt = currRowLasersCnt;
            }
        }

        return beamsCnt;
    }

    static final class BenchmarkRunner {
        public static void main(String[] args) throws IOException {
            org.openjdk.jmh.Main.main(args);
        }
    }
}
