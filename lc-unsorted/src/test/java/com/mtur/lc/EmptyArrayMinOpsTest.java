package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class EmptyArrayMinOpsTest {
    @Test
    void testCorrectness() {
        EmptyArrayMinOps logic = new EmptyArrayMinOps();
//        int[] ints = DataGenerator.generateUnsortedArray(10, 0, 3);
        assertEquals(4, logic.minOperations(new int[]{2,3,3,2,2,4,2,3,4}));
        assertEquals(-1, logic.minOperations(new int[]{2,1,2,2,3,3}));
    }
}