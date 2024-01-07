package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class MaxJobProfit2Clean {
    long iters = 0;
    long reqCount = 0;

    private int[] idMap;
    private int[] calcProfits;
    private int[] startTime;
    private int[] endTime;
    private int[] profit;

    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        iters = 0;
        reqCount = 0;
        int length = startTime.length;

        this.startTime = startTime;
        this.endTime = endTime;
        this.profit = profit;
        idMap = makeSortedIds(startTime);
        calcProfits = new int[length];

        int maxProfit = 0;

        for (int i = 0; i < length; i++) {
            int realId = idMap[i];
            if(calcProfits[realId] > 0) {
                continue;
            }
            int currJobMaxProfit = calcJobMaxProfit(i);
            maxProfit = Math.max(currJobMaxProfit, maxProfit);
        }

        log.warn("Iters: {} for elements: {}, recCount: {}", iters, length, reqCount);

        return maxProfit;
    }

    private int calcJobMaxProfit(int i) {
        int realId = idMap[i];

        if (calcProfits[realId] != 0) {
            return calcProfits[realId];
        }

        int currEnd = endTime[realId];
        int maxNextProfit = 0;
        for (int next = i + 1; next < startTime.length; next++) {
            iters++;
            int nextRealId = idMap[next];
            if (startTime[nextRealId] < currEnd) {
                continue;
            }

            int nextProfit = calcProfits[nextRealId];
            if (nextProfit == 0) {
                reqCount++;
                nextProfit = calcJobMaxProfit(next);
            }

            maxNextProfit = Math.max(maxNextProfit, nextProfit);
        }

        int resProfit = profit[realId] + maxNextProfit;
        calcProfits[realId] = resProfit;
        return resProfit;
    }

    private int[] makeSortedIds(int[] sortByArrayAsc) {
        int length = sortByArrayAsc.length;
        Integer[] sortedIds = new Integer[length];

        // initial mapping
        for (int i = 0; i < length; i++) {
            sortedIds[i] = i;
        }

        // sort ids by time but preserve mapping
        Arrays.sort(sortedIds, (a, b) -> Integer.compare(sortByArrayAsc[a], sortByArrayAsc[b]));

        return Arrays.stream(sortedIds).mapToInt(Integer::intValue).toArray();
    }
}