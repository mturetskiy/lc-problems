package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class MaxJobProfit implements MaxJobProfitSolver {
    long iters = 0;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        iters = 0;
        int length = startTime.length;
        int[] sortedIds = makeSortedIds(startTime);

        int[] calcProfits = new int[length];
        int maxProfit = 0;

        for (int i = 0; i < length; i++) {
//            log.info("High level: {}", i);
            int currJobMaxProfit = calcJobMaxProfit(i, calcProfits, sortedIds, startTime, endTime, profit);
            maxProfit = Math.max(currJobMaxProfit, maxProfit);
        }



        log.info("Iters: {} for elements: {}", iters, length);

        return maxProfit;
    }

    private int[] makeSortedIds(int[] sortByArray) {
        Integer[] sortedIds = new Integer[sortByArray.length];

        // initial mapping
        for (int i = 0; i < sortByArray.length; i++) {
            sortedIds[i] = i;
        }

        // sort ids by time but preserve mapping
        Arrays.sort(sortedIds, (a, b) -> Integer.compare(sortByArray[a], sortByArray[b]));

        return Arrays.stream(sortedIds).mapToInt(Integer::intValue).toArray();
    }

    /**
     i - index of sorted ids array, 0..N
     */
    private int calcJobMaxProfit(int i, int[] calcProfits, int[] idsMapping, int[] startTime, int[] endTime, int[] profit) {

        int realId = idsMapping[i];
        int currProfit = profit[realId];



        if (i == startTime.length - 1) { // last job
            return currProfit;
        }

//        if (calcProfits[realId] != 0) {
//            return calcProfits[realId];
//        }

//        log.info("calc for: {}, profit: {}", realId, currProfit);

        int currEnd = endTime[realId];
        int maxNextProfit = 0;

        for (int next = i + 1; next < startTime.length; next++) {
            iters++;
            if (startTime[idsMapping[next]] < currEnd) {
                continue;
            }

            int nextProfit = calcJobMaxProfit(next, calcProfits, idsMapping, startTime, endTime, profit);;
            maxNextProfit = Math.max(maxNextProfit, nextProfit);
        }

        int resProfit = currProfit + maxNextProfit;
        calcProfits[realId] = resProfit;
        return resProfit;
    }
}