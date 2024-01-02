package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
public class TestUtils {
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

}
