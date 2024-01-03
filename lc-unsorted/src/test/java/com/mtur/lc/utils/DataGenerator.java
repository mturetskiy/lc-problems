package com.mtur.lc.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

@Slf4j
public class DataGenerator {
    public static int[] generateUnsortedArray(int size) {
        log.info("Generating unsorted array of {} elements.", size);
        int[] res = new int[size];

        for (int i = 0; i < size; i++) {
            res[i] = RandomUtils.nextInt(0, size);
        }

        return res;
    }

    public static String generateStr(int len, char... chars) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int charIdx = RandomUtils.nextInt(0, chars.length);
            sb.append(chars[charIdx]);
        }

        return sb.toString();
    }

    public static String[] generateStrMatrix(int rows, int width, char... chars) {
        String[] matrix = new String[rows];
        for (int i = 0; i < rows; i++) {
            matrix[i] = generateStr(width, chars);
        }

        return matrix;
    }
}
