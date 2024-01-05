package com.mtur.lc;

import com.mtur.lc.utils.DataGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class EmptyArrayMinOpsTest {
    @Test
    void testCorrectness() {
        EmptyArrayMinOps logic = new EmptyArrayMinOps();
//        int[] ints = DataGenerator.generateUnsortedArray(10, 0, 3);
        assertEquals(4, logic.minOperations(new int[]{1,3,3,1,1,1_000_000,1,3,1_000_000}));
        assertEquals(4, logic.minOperations(new int[]{2,1_000_000,1_000_000,2,2,4,2,1_000_000,4}));
        assertEquals(4, logic.minOperations(new int[]{1_000_000,3,3,1_000_000,1_000_000,4,1_000_000,3,4}));
        assertEquals(4, logic.minOperations(new int[]{2,3,3,2,2,4,2,3,4}));
        assertEquals(-1, logic.minOperations(new int[]{2,1,2,2,3,3}));
        assertEquals(-1, logic.minOperations(new int[]{1,2,3,4,5,6}));
    }

    @Test
    void testManyElements() {
        EmptyArrayMinOps logic = new EmptyArrayMinOps();
        int[] ints = DataGenerator.generateUnsortedArray(100_000, 960_000, 1_000_000);
        logic.minOperations(ints);
    }

    @Test
    void testCorrectnessForOpt() {
        EmptyArrayMinOpsOpt logic = new EmptyArrayMinOpsOpt();
//        int[] ints = DataGenerator.generateUnsortedArray(10, 0, 3);
        assertEquals(4, logic.minOperations(new int[]{1,3,3,1,1,1_000_000,1,3,1_000_000}));
        assertEquals(4, logic.minOperations(new int[]{2,1_000_000,1_000_000,2,2,4,2,1_000_000,4}));
        assertEquals(4, logic.minOperations(new int[]{1_000_000,3,3,1_000_000,1_000_000,4,1_000_000,3,4}));
        assertEquals(4, logic.minOperations(new int[]{2,3,3,2,2,4,2,3,4}));
        assertEquals(-1, logic.minOperations(new int[]{2,1,2,2,3,3}));
        assertEquals(-1, logic.minOperations(new int[]{1,2,3,4,5,6}));
    }

    @Test
    void testCorrectnessForOpt2() {
        EmptyArrayMinOpsOpt2 logic = new EmptyArrayMinOpsOpt2();
//        int[] ints = DataGenerator.generateUnsortedArray(10, 0, 3);
        assertEquals(4, logic.minOperations(new int[]{1,3,3,1,1,1_000_000,1,3,1_000_000}));
        assertEquals(4, logic.minOperations(new int[]{2,1_000_000,1_000_000,2,2,4,2,1_000_000,4}));
        assertEquals(4, logic.minOperations(new int[]{1_000_000,3,3,1_000_000,1_000_000,4,1_000_000,3,4}));
        assertEquals(4, logic.minOperations(new int[]{2,3,3,2,2,4,2,3,4}));
        assertEquals(-1, logic.minOperations(new int[]{1,2,3,4,5,6}));
        assertEquals(-1, logic.minOperations(new int[]{2,1,2,2,3,3}));
    }
}