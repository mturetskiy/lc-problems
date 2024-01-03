package com.mtur.lc;

import com.mtur.lc.utils.DataGenerator;
import com.mtur.lc.utils.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.mtur.lc.utils.TestUtils.measureDuration;
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
    void testFindMatrixPerfComparison() throws Exception {
        int[] ints = DataGenerator.generateUnsortedArray(200);

        measureDuration("Base", () -> new ArrayTo2dArray().findMatrix(ints));
        measureDuration("Fast LL", () -> new ArrayTo2dArrayFastLL().findMatrix(ints));
        measureDuration("Fast AL", () -> new ArrayTo2dArrayFastAL().findMatrix(ints));
        measureDuration("Opt", () -> new ArrayTo2dArrayOpt().findMatrix(ints));
    }
}