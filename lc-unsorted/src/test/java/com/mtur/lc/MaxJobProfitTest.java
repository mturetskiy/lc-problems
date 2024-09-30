package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static com.mtur.lc.utils.TestUtils.loadIntsFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class MaxJobProfitTest {
    @Test
    void testHighVolume10k() {
        MaxJobProfit2 code = new MaxJobProfit2();
        int[] startTime = loadIntsFromFile("/start-times-cut");
        int[] endTime = loadIntsFromFile("/end-times-cut");
        int[] profit = loadIntsFromFile("/profits-cut");

        long start = System.currentTimeMillis();
        assertEquals(500, code.jobScheduling(startTime, endTime, profit));
        log.warn("Time: {} ms", System.currentTimeMillis() - start);
    }

    @Test
    void testCutCorrect() {
        MaxJobProfit code = new MaxJobProfit();
        int[] startTime = loadIntsFromFile("/start-times-cut");
        int[] endTime = loadIntsFromFile("/end-times-cut");
        int[] profit = loadIntsFromFile("/profits-cut");


        ArrayList<Integer> allTimes = new ArrayList<>(startTime.length);

        Arrays.stream(startTime).forEach(t -> allTimes.add(t));
        Arrays.stream(endTime).forEach(t -> allTimes.add(t));
        Collections.sort(allTimes);

        log.info("Times: {}", allTimes);


        long start = System.currentTimeMillis();
        int result = code.jobScheduling(startTime, endTime, profit);
        log.warn("Result: {}, Time: {} ms", result, System.currentTimeMillis() - start);
    }

    @Test
    void testHighVolume30k() {
        MaxJobProfit2 code = new MaxJobProfit2();
        int[] startTime = loadIntsFromFile("/start-times");
        int[] endTime = loadIntsFromFile("/end-times");
        int[] profit = loadIntsFromFile("/profits");

        long start = System.currentTimeMillis();
        assertEquals(4611745, code.jobScheduling(startTime, endTime, profit));
        log.warn("Time: {} ms", System.currentTimeMillis() - start);

    }

    @Test
    void testHighVolume50k() {
        MaxJobProfit2 code = new MaxJobProfit2();
        int[] startTime = loadIntsFromFile("/start-time2");
        int[] endTime = loadIntsFromFile("/end-time2");
        int[] profit = loadIntsFromFile("/profit2");

        long start = System.currentTimeMillis();
        assertEquals(76390261, code.jobScheduling(startTime, endTime, profit));
        log.warn("Time: {} ms", System.currentTimeMillis() - start);

    }

    @Test
    void testCorrectness() {
        MaxJobProfit2 code = new MaxJobProfit2();
        assertEquals(260, code.jobScheduling(new int[]{1,2,3,4,6}, new int[]{3,5,10,6,9}, new int[]{20,200,100,70,60}));

    }

    @Test
    void testMaxJobProfit() {
        testBaseCases(new MaxJobProfit());
    }

    @Test
    void testMaxJobProfit2() {
        testBaseCases(new MaxJobProfit2());
    }

    @Test
    void testMaxJobProfit2Clean() {
        testBaseCases(new MaxJobProfit2Clean());
    }

    @Test
    void testMaxJobProfitOpt() {
        testBaseCases(new MaxJobProfitOpt());
    }

    private void testBaseCases(MaxJobProfitSolver solver) {
        assertEquals(24, solver.jobScheduling(new int[]{1,4,8,2,5,3,2}, new int[]{2,6,9,5,8,7,8}, new int[]{1,20,3,4,5,6,7}));
        assertEquals(265, solver.jobScheduling(new int[]{1, 2, 2, 3, 6, 3, 0, 1}, new int[]{3, 3, 4, 6, 8, 7, 4, 2}, new int[]{50, 5, 55, 70, 40, 100, 200, 150}));
        assertEquals(200, solver.jobScheduling(new int[]{1, 2, 2, 3, 6, 3}, new int[]{3, 3, 4, 6, 8, 7}, new int[]{50, 80, 10, 70, 40, 120}));
        assertEquals(6, solver.jobScheduling(new int[]{1, 1, 1}, new int[]{2, 3, 4}, new int[]{5, 6, 4}));
        assertEquals(20, solver.jobScheduling(new int[]{2, 1}, new int[]{4, 5}, new int[]{10, 20}));
        assertEquals(200, solver.jobScheduling(new int[]{1, 4, 4}, new int[]{5, 5, 5}, new int[]{100, 200, 50}));
        assertEquals(120, solver.jobScheduling(new int[]{1, 2, 5, 7}, new int[]{8, 3, 6, 8}, new int[]{100, 40, 40, 40}));
        assertEquals(150, solver.jobScheduling(new int[]{1,2,3,4,6}, new int[]{3,5,10,6,9}, new int[]{20,20,100,70,60}));
        assertEquals(260, solver.jobScheduling(new int[]{1,2,3,4,6}, new int[]{3,5,10,6,9}, new int[]{20,200,100,70,60}));
        assertEquals(41, solver.jobScheduling(new int[]{6,15,7,11,1,3,16,2}, new int[]{19,18,19,16,10,8,19,8}, new int[]{2,9,1,19,5,7,3,19}));

    }
}