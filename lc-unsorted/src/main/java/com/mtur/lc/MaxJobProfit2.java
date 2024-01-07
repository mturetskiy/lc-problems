package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedList;

@Slf4j
public class MaxJobProfit2 {
    int iters = 0;
    int recSkips = 0;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        iters = 0;
        recSkips = 0;
        int length = startTime.length;
        int[] sortedIds = makeSortedIds(startTime, profit);

        int[] calcProfits = new int[length];
        LinkedList<Integer> todo = new LinkedList<>();
        int maxProfit = 0;

        for (int i = 0; i < length; i++) {
            int realId = sortedIds[i];
            if(calcProfits[realId] > 0) {
                log.info("Skip {} - already calculated in sub calcs.", profit[realId]);
                continue;
            }
            log.info("High level start at: {}", profit[realId]);
//            todo.addLast(i);
            int currJobMaxProfit = calcJobMaxProfit("", i, calcProfits, sortedIds, startTime, endTime, profit, todo);
            maxProfit = Math.max(currJobMaxProfit, maxProfit);
        }



        log.warn("Iters: {} for elements: {}, recSkips: {}", iters, length, recSkips);

        return maxProfit;
    }

    /**
     i - index of sorted ids array, 0..N
     */
    private int calcJobMaxProfit(String prefix, int i, int[] calcProfits, int[] idsMapping, int[] startTime, int[] endTime,
                                 int[] profit, LinkedList<Integer> todo) {
        int realId = idsMapping[i];
        int currProfit = profit[realId];

        log.info("{}Calc for: {}", prefix, currProfit);

        if (i == startTime.length - 1) {
            calcProfits[realId] = currProfit;
            return currProfit;
        }

        if (calcProfits[realId] != 0) {
            log.info("{}Already calculated for {} => {}", prefix, currProfit, calcProfits[realId]);
            return calcProfits[realId];
        }


        int currEnd = endTime[realId];
        int maxNextProfit = 0;


//        todo.addLast(i);
//
//        while(todo.peek() != null) {
//            int current = todo.removeLast();

            int next = ++i;
            while (next < idsMapping.length - 1 && startTime[idsMapping[next]] < currEnd) {
                todo.addLast(next);
                log.info("{} At {} skipping {}", prefix, currProfit, profit[idsMapping[next]]);
                next++;
            }

            int nextRealId = idsMapping[next];
            log.info("{} Found next suitable job: {}", prefix, profit[nextRealId]);


            int nextProfit = calcProfits[nextRealId];
            if (nextProfit == 0) {
                log.info("{}Take {} for recursion", prefix, profit[nextRealId]);
//                nextProfit = calcJobMaxProfit(null/*prefix + "\t"*/, next, nextInitProfit, calcProfits, idsMapping, startTime, endTime, profit, visited, skipped);
                nextProfit = calcJobMaxProfit(prefix + "\t", next, calcProfits, idsMapping, startTime, endTime, profit, todo);
            } else {
                recSkips++;
                log.info("{}Skip {} for recursion: already calculated.", prefix, profit[nextRealId]);
            }

            maxNextProfit = Math.max(maxNextProfit, nextProfit);

        for (Integer td : todo) {
            log.info("{} For {} Skipped: {}", prefix, currProfit, profit[idsMapping[td]]);
        }

//        }



//        for (int next = i + 1; next < startTime.length; next++) {
//            iters++;
//            int nextRealId = idsMapping[next];
//            if (startTime[nextRealId] < currEnd) {
//                log.info("{} Skip {} because intersects.", prefix, profit[nextRealId]);
//                continue;
//            }
//
//
//            int nextProfit = calcProfits[nextRealId];
//            if (nextProfit == 0) {
//                log.info("{}Take {} for recursion, with next init profit: {}", prefix, profit[nextRealId], nextInitProfit);
////                nextProfit = calcJobMaxProfit(null/*prefix + "\t"*/, next, nextInitProfit, calcProfits, idsMapping, startTime, endTime, profit, visited, skipped);
//                nextProfit = calcJobMaxProfit(prefix + "\t", next, nextInitProfit, calcProfits, idsMapping, startTime, endTime, profit, visited, todo);
//                log.info("{}Skipped for : {} => {}", prefix, profit[nextRealId], todo);
//            } else {
//                recSkips++;
//                log.info("{}Skip {} for recursion: already calculated.", prefix, profit[nextRealId]);
//            }
//
//            maxNextProfit = Math.max(maxNextProfit, nextProfit);
//
//        }


        int resProfit = currProfit + maxNextProfit;
        log.info("{}For {} resolved max profit as: {}", prefix, currProfit, resProfit);
        calcProfits[realId] = resProfit;
        log.info("{}---------", prefix);
        return resProfit;
    }

    private int[] makeSortedIds(int[] sortByArrayAsc, int[] sortByArray2Desc) {
        int length = sortByArrayAsc.length;
        Integer[] sortedIds = new Integer[length];

        // initial mapping
        for (int i = 0; i < length; i++) {
            sortedIds[i] = i;
        }

        // sort ids by time but preserve mapping
        Arrays.sort(sortedIds, (a, b) -> {
            int compare = Integer.compare(sortByArrayAsc[a], sortByArrayAsc[b]);
            if (compare == 0) { // second criteria, DESC
                compare = Integer.compare(sortByArray2Desc[b], sortByArray2Desc[a]);
            }

            return compare;
        });

        return Arrays.stream(sortedIds).mapToInt(Integer::intValue).toArray();
    }
}