package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedList;

@Slf4j
public class MaxJobProfitOpt {
    int iters = 0;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int length = startTime.length;
        log.info("Start for {} elements", length);
        int[] idsMap = makeSortedIds(startTime);


        int maxProfit = 0;

        // realId, initial profit
        LinkedList<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[]{0, 0});

        while (toVisit.peek() != null) {
            int[] tv = toVisit.pop();
            int id = tv[0];
            int realId = idsMap[id];
            int currEnd = endTime[realId];
            int currProfit = profit[realId];
            int initProfit = tv[1];


//            log.info("--- Processing: {}, init profit: {}", currProfit, initProfit);

            int nextAccumProfit = initProfit + currProfit;
            int lastAddedProfit = currProfit;

            for (int next = id + 1; next < length; next++) {
                iters++;
                int nextId = idsMap[next];
                if (startTime[nextId] < currEnd) {
                    toVisit.push(new int[]{next, nextAccumProfit - lastAddedProfit});
//                    log.info("to visit: {}[{}]/{}", profit[nextId], next, nextAccumProfit - lastAddedProfit);
                    continue;
                }



                nextAccumProfit += profit[nextId];
                currEnd = endTime[nextId];
                lastAddedProfit = profit[nextId];
            }



            maxProfit = Math.max(nextAccumProfit, maxProfit);


//            log.info("For {}[{}] profit is: {}", profit[realId], realId, nextAccumProfit);
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

        if (calcProfits[realId] != 0) {
            return calcProfits[realId];
        }

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