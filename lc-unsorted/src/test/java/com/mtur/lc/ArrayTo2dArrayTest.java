package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class ArrayTo2dArrayTest {
    @Test
    void testFindMatrixCorrectness() {
        int[] ints = {11, 5, 3, 9, 3, 7, 6, 6, 5, 1, 9, 6, 18, 1, 17, 7, 4, 4, 19, 3};
        assertMatrixIsCorrect(new ArrayTo2dArray().findMatrix(ints));
        assertMatrixIsCorrect(new ArrayTo2dArrayFastLL().findMatrix(ints));
        assertMatrixIsCorrect(new ArrayTo2dArrayFastLL().findMatrix(ints));
        assertMatrixIsCorrect(new ArrayTo2dArrayOpt().findMatrix(ints));
    }

    private void assertMatrixIsCorrect(List<List<Integer>> matrix) {
        log.info("Result matrix:");
        TestUtils.printMatrix(matrix);

        assertTrue(matrix.get(0).containsAll(Arrays.asList(11, 5, 3, 9, 7, 6, 1, 18, 17, 4, 19)));
        assertTrue(matrix.get(1).containsAll(Arrays.asList(3, 6, 5, 9, 1, 7, 4)));
        assertTrue(matrix.get(2).containsAll(Arrays.asList(6, 3)));
    }

    @Test
    void testFindMatrixPerfComparison() throws InterruptedException {
        int[] ints = DataGenerator.generateUnsortedArray(200);
        log.info("Warmup ...");
        new ArrayTo2dArray().findMatrix(ints);
        log.info("Warmup is done.");

        long start1 = System.nanoTime();
        new ArrayTo2dArray().findMatrix(ints);
        log.info("[Normal] Done in: {} mks.", (System.nanoTime() - start1) / 1000.0f);

        Runtime.getRuntime().gc();
        Thread.sleep(2000);
        new ArrayTo2dArrayFastLL().findMatrix(ints);
        long start2 = System.nanoTime();
        new ArrayTo2dArrayFastLL().findMatrix(ints);
        log.info("[Fast LL] Done in: {} mks.", (System.nanoTime() - start2) / 1000.0f);

        Runtime.getRuntime().gc();
        Thread.sleep(2000);
        new ArrayTo2dArrayFastAL().findMatrix(ints);
        long start3 = System.nanoTime();
        new ArrayTo2dArrayFastAL().findMatrix(ints);
        log.info("[Fast AL] Done in: {} mks.", (System.nanoTime() - start3) / 1000.0f);


        Runtime.getRuntime().gc();
        Thread.sleep(2000);
        new ArrayTo2dArrayOpt().findMatrix(ints);
        long start4 = System.nanoTime();
        new ArrayTo2dArrayOpt().findMatrix(ints);
        log.info("[Fast AL] Done in: {} mks.", (System.nanoTime() - start4) / 1000.0f);
    }
}