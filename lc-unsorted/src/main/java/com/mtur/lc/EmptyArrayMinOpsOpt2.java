package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EmptyArrayMinOpsOpt2 {
    public int minOperations(int[] nums) {
        Map<Integer, int[]> numCounts = new HashMap<>();

        int op2TotalCount = 0;

        for (int num : nums) {
            int[] nCounts = numCounts.computeIfAbsent(num, n -> new int[2]);
            nCounts[0]++;
            int currCount = ++nCounts[1];

            if (currCount == 3) {
                op2TotalCount++;
                nCounts[1] = 0;
            }
        }

        int op1TotalCount = 0;
        for (int[] nCounts : numCounts.values()) {
            int rem = nCounts[1];
            if (rem == 0) { // that number was already counted
                continue;
            }

            if (rem == 1 && nCounts[0] == 1) { // there is number that was seen only once.
                return -1;
            }

            if (rem == 1) { //
                op2TotalCount--;
                op1TotalCount += 2;
            } else if (rem == 2) {
                op1TotalCount++;
            }
        }

        return op1TotalCount + op2TotalCount;
    }
}