package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.LinkedList;

@Slf4j
public class MaxJobProfitOpt2 {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        long iters = 0;
        long todoSkips = 0;


        int length = startTime.length;
        int[] idsMap = makeSortedIds(startTime, profit);
        int maxProfit = 0;
        int[] startTimeNextJob = calcStartTimeNextJob(idsMap, startTime, endTime, profit);

        LinkedList<int[]> todo = new LinkedList<>();
        int[] todoInitProfits = new int[length];

        int[] visited = new int[length];

        Arrays.fill(todoInitProfits, -1);

        todo.push(new int[]{0, 0});

        while (todo.peek() != null) {
            int[] e = todo.removeFirst();
            int id = e[0];
            int realId = idsMap[id];


            int initProfit = e[1];
            int currEnd = endTime[idsMap[id]];
            int currProfit = profit[realId];
            int nextAccumProfit = initProfit + currProfit;
            int lastAddedProfit = currProfit;


            if (id < length - 1) {
                if (todoInitProfits[realId] > initProfit) {
                    todoSkips++;
                    log.info("Skip todo: {} because there are more for it with better profit: {} > {}", currProfit, todoInitProfits[realId], initProfit);
                    continue;
                }

                log.info("--- Processing: {}, init profit: {}", currProfit, initProfit);





                if (id == length - 1) {

                }


                int nextSuitableJobIdx = startTimeNextJob[currEnd];
                if (nextSuitableJobIdx < 0) {
                    nextSuitableJobIdx = id + 1;
                    log.info("unable to get next job idx based on time. using next job id: {}", nextSuitableJobIdx);
                }

                for (int next = nextSuitableJobIdx; next < length; next++) {
                    iters++;
                    int nextRealId = idsMap[next];

                    if (startTime[nextRealId] < currEnd) {
                        int initNextProfit = nextAccumProfit - lastAddedProfit;
                        log.info("{} intersects, try to schedule with init profit: {}", profit[nextRealId], initNextProfit);
                        boolean alreadyScheduledWithHigherProfit = todoInitProfits[nextRealId] >= initNextProfit;
                        boolean alreadyVisitedWithHigherProfit = visited[nextRealId] > initNextProfit;

                        if (!alreadyVisitedWithHigherProfit) {
                            if (!alreadyScheduledWithHigherProfit) {
                                todo.addLast(new int[]{next, initNextProfit});
                                todoInitProfits[nextRealId] = initNextProfit;
                                log.info("\t\tto visit: {}/{}", profit[nextRealId], initNextProfit);
                            } else {
                                log.info("Skip {} because already in todo with higher init profit: {} > {}", profit[nextRealId], visited[nextRealId], initNextProfit);
                            }
                        } else {
                            log.info("Skip {} because already visited  with higher profit: {} > {}", profit[nextRealId], visited[nextRealId], initNextProfit);
                        }

                        continue;
                    }

                    log.info("  take {} with init profit: {}", profit[nextRealId], nextAccumProfit);
                    visited[nextRealId] = nextAccumProfit; // mark this as was considered with current init profit

                    nextAccumProfit += profit[nextRealId];
                    currEnd = endTime[nextRealId];
                    lastAddedProfit = profit[nextRealId];
                }
            }


            maxProfit = Math.max(nextAccumProfit, maxProfit);
            log.info("For {} profit is: {}", profit[realId], nextAccumProfit);
            log.info("-------");
        }

        log.info("Iters: {}, todoSkips: {}, count: {}", iters, todoSkips, length);

        return maxProfit;
    }


    private int[] calcStartTimeNextJob(int[] idsMap, int[] startTime, int[] endTime, int[] profit) {
        int length = startTime.length;

        int minStartTime = startTime[idsMap[0]];
        int maxStartTime = startTime[idsMap[length - 1]];

        int[] startTimeMap = new int[maxStartTime + 1];
        Arrays.fill(startTimeMap, -1);

        int lastStartTime = -1;



        for (int i = 0; i < length; i++) {
            int realId = idsMap[i];
            int currProfit = profit[realId];
            int currStartTime = startTime[realId];

            log.info("Check: {}, start: {}", currProfit, currStartTime);

            if (currStartTime > lastStartTime) {
                lastStartTime = currStartTime;
                startTimeMap[currStartTime] = i;
                log.info("Use {}, start: {}", currProfit, currStartTime);
            } else {
                log.info("Skip {}, start: {}", currProfit, currStartTime);
            }
        }

        log.info("Min startTime: {}, maxStartTime: {}", minStartTime, maxStartTime);

        int prevMaxStart = startTimeMap[maxStartTime];
        for (int i = maxStartTime - 1; i >= minStartTime; i--) { // todo: inefficient
            if (startTimeMap[i] == -1) {
                log.info("Filling start time at {} with prev: {}", i, prevMaxStart);
                startTimeMap[i] = prevMaxStart;
            } else {
                prevMaxStart = startTimeMap[i];
            }
        }

        log.info("Result: {}", Arrays.toString(startTimeMap));


        return startTimeMap;
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