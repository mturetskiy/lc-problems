package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaxJobProfitOptSort3 implements MaxJobProfitSolver {
    int iters = 0;
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int length = startTime.length;
        quicksort_r(startTime, endTime, profit, 0, length);

        int[] calcProfits = new int[length];
        int maxProfit = 0;

        for (int i = 0; i < length; i++) {
            int currJobMaxProfit = calcJobMaxProfit(i, calcProfits, startTime, endTime, profit);
            maxProfit = Math.max(currJobMaxProfit, maxProfit);
        }

        log.info("Iters: {} for elements: {}", iters, length);

        return maxProfit;
    }


    /**
     i - index of sorted ids array, 0..N
     */
    private int calcJobMaxProfit(int i, int[] calcProfits, int[] startTime, int[] endTime, int[] profit) {
        if (i == startTime.length - 1) { // last job
            return profit[i];
        }

        if (calcProfits[i] != 0) {
            return calcProfits[i];
        }

//        log.info("calc for: {}, profit: {}", realId, currProfit);

        int currEnd = endTime[i];
        int maxNextProfit = 0;

        for (int next = i + 1; next < startTime.length; next++) {
            iters++;
            if (startTime[next] < currEnd) {
                continue;
            }

            int nextProfit = calcJobMaxProfit(next, calcProfits, startTime, endTime, profit);;
            maxNextProfit = Math.max(maxNextProfit, nextProfit);
        }

        int resProfit = profit[i] + maxNextProfit;
        calcProfits[i] = resProfit;
        return resProfit;
    }

    protected void quicksort_r(int[] data, int[] data1, int[] data2, int start, int end) {
        int length = end - start;

        if (length > 1) {
            int pivotIndex = partition(data, data1, data2, start, end);
            quicksort_r(data, data1, data2, start, pivotIndex);
            quicksort_r(data, data1, data2, pivotIndex + 1, end);
        }
    }


    // returns index of pivot after partitioning
    protected int partition(int[] data, int[] data1, int[] data2, int start, int end) {
        // select pivot:
        int pivotIndex = end - 1;
        int pivot = data[pivotIndex];

        int firstHigh = start;
        for (int i = start; i < end - 1; i++) {
            if (data[i] < pivot) {
                swap(data, i, firstHigh);
                swap(data1, i, firstHigh);
                swap(data2, i, firstHigh);
                firstHigh++;
            }
        }

        swap(data, firstHigh, pivotIndex);
        swap(data1, firstHigh, pivotIndex);
        swap(data2, firstHigh, pivotIndex);
        return firstHigh;
    }

    private void swap(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}