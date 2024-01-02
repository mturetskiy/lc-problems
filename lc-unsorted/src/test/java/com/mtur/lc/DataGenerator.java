package com.mtur.lc;

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
}
