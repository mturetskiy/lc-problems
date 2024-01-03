package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.mtur.lc.utils.DataGenerator.generateStrMatrix;
import static com.mtur.lc.utils.TestUtils.measureDuration;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class LaserBeamsTest {
    @Test
    void testLaserBeams() {
        LaserBeams laserBeams = new LaserBeams();
        LaserBeamsOpt laserBeamsOpt = new LaserBeamsOpt();
        LaserBeamsOpt2 laserBeamsOpt2 = new LaserBeamsOpt2();

        assertEquals(8, laserBeams.numberOfBeams(new String[]{"011001", "000000", "010100", "001000"}));
        assertEquals(0, laserBeams.numberOfBeams(new String[]{"0000", "0110", "0000"}));

        assertEquals(8, laserBeamsOpt.numberOfBeams(new String[]{"011001", "000000", "010100", "001000"}));
        assertEquals(0, laserBeamsOpt.numberOfBeams(new String[]{"0000", "0110", "0000"}));

        assertEquals(8, laserBeamsOpt2.numberOfBeams(new String[]{"011001", "000000", "010100", "001000"}));
        assertEquals(0, laserBeamsOpt2.numberOfBeams(new String[]{"0000", "0110", "0000"}));
    }

    @Test
    void testLaserBeamPerf() throws Exception {
        int rows = 1_000;
        int cols = 100_000;
        LaserBeams laserBeamsBase = new LaserBeams();
        LaserBeamsOpt laserBeamsOpt = new LaserBeamsOpt();
        LaserBeamsOpt2 laserBeamsOpt2 = new LaserBeamsOpt2();

        String[] matrix = generateStrMatrix(rows, cols, '0', '1');

        log.info("Starting.");
        measureDuration("Base", () -> laserBeamsBase.numberOfBeams(matrix));
        measureDuration("Opt", () -> laserBeamsOpt.numberOfBeams(matrix));
        measureDuration("Opt2", () -> laserBeamsOpt2.numberOfBeams(matrix));

        measureDuration("Base", () -> laserBeamsBase.numberOfBeams(matrix));
        measureDuration("Opt", () -> laserBeamsOpt.numberOfBeams(matrix));
        measureDuration("Opt2", () -> laserBeamsOpt2.numberOfBeams(matrix));

        measureDuration("Base", () -> laserBeamsBase.numberOfBeams(matrix));
        measureDuration("Opt", () -> laserBeamsOpt.numberOfBeams(matrix));
        measureDuration("Opt2", () -> laserBeamsOpt2.numberOfBeams(matrix));
    }
}