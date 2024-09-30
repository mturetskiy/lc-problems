package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedList;

@Slf4j
public class MaxJobProfit2 implements MaxJobProfitSolver {
    int iters = 0;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        iters = 0;
        int length = startTime.length;
        int[] idMap = makeSortedIds(startTime, profit);

        int[] visited = new int[length];
        int maxProfit = 0;


        LinkedList<Integer> toVisit = new LinkedList<>();
        toVisit.addLast(0);

        while (toVisit.peek() != null) {
            Integer i = toVisit.removeFirst();
            log.info("Processing: {}", profit[idMap[i]]);
        }



/*

        for (int i = 0; i < length; i++) {
            int realTopId = idMap[i];
            if (visited[realTopId] > 0) {
                log.info("Skip top: {}, already visited with: {}", profit[realTopId], visited[realTopId]);
                continue;
            }

            log.info("Top processing: {}", profit[realTopId]);

            for (int start = i + 1; start < length; start++) {
                int realStartId = idMap[start];


                if (visited[realStartId] >= profit[realTopId]) {
                    log.info("\t Skip start: {}, already visited with: {} > {}", profit[realStartId], visited[realStartId], profit[realTopId]);
                    continue;
                }

                int currStartSumProfit = profit[realTopId];
                int currEnd = endTime[realTopId];
                int currEndP = profit[realTopId];

                log.info("");
                log.info("\t Start from: {}", profit[realStartId]);


                for (int next = start; next < length; next++) {
                    iters++;
                    int realNextId = idMap[next];
                    if (visited[realNextId] >= currStartSumProfit) {
                        log.info("\t\t Skip sub: {}, already visited with: {} > {}", profit[realNextId], visited[realNextId], currStartSumProfit);
                        continue;
                    }




                    int nextP = profit[realNextId];
                    if (startTime[realNextId] < currEnd) { // skip this
                        log.info("\t\t [x] Skip: {}, intersects with: {}", nextP, currEndP);
                        // todo: move start if all skips in a row:
                        continue;
                    } else {
                        visited[realNextId] = currStartSumProfit; // profit we visit it with (excl current)
                        log.info("\t\t     Store visited: {} => {}", nextP, visited[realNextId]);
                        currEnd = endTime[realNextId];
                        currEndP = nextP;
                        currStartSumProfit += nextP;


                        log.info("\t\t [v] Accept: {}. Curr sum profit: {}", nextP, currStartSumProfit);
                    }
                }

                maxProfit = Math.max(maxProfit, currStartSumProfit);
                if (maxProfit == currStartSumProfit) {
                    log.info("\t Got new max: {}", maxProfit);
                }
                log.info("\t For start {} sum profit: {}", profit[realStartId], currStartSumProfit);


            }

            log.info("--------------------------------");
        }

*/

        log.warn("Iters: {} for elements: {}, ", iters, length);

        return maxProfit;
    }


    private int[] makeSortedIds(int[] sortByArrayAsc, int[] profit) {
        int length = sortByArrayAsc.length;
        Integer[] sortedIds = new Integer[length];

        // initial mapping
        for (int i = 0; i < length; i++) {
            sortedIds[i] = i;
        }

        // sort ids by time but preserve mapping
        Arrays.sort(sortedIds, (a, b) -> {
            int compare = Integer.compare(sortByArrayAsc[a], sortByArrayAsc[b]);
//            if (compare == 0) { // second criteria, DESC
//                compare = Integer.compare(sortByArray2Desc[b], sortByArray2Desc[a]);
//            }

            return compare;
        });

//        log.info("Sorted array:");
//        for (int i = 0; i < length; i++) {
//            log.info("  {}: {} [{}]", i, profit[sortedIds[i]], sortedIds[i]);
//        }

        return Arrays.stream(sortedIds).mapToInt(Integer::intValue).toArray();
    }
}