package com.mtur.lc;

import java.util.HashMap;
import java.util.Map;

public class EmptyArrayMinOps {
    // Special const that helps to separate numbers that can be removed by 2nd op (seen 3+ times) from others cases.
    private static final int FIRST_OCCURRENCE_BASE = 1_000_000;
    // This const helps to identify if number was seen only once.
    private static final int FIRST_OCCURRENCE_NO_OP = 1_000_001;
    private static final int FIRST_OCCURRENCE_2OP = 1_000_003;
    private static final int FIRST_OCCURRENCE_1OP = 1_000_002;
    public int minOperations(int[] nums) {
        Map<Integer, Integer> numCounts = new HashMap<>();

        int op2TotalCount = 0;

        // After this loop numCounts contains remainders by 2nd op or special constants if a number was seen less than 3 times.
        for (int num : nums) {
            int nCount = numCounts.computeIfAbsent(num, n -> FIRST_OCCURRENCE_BASE);
            nCount++;
            if (nCount == 3 || nCount == FIRST_OCCURRENCE_2OP) {
                op2TotalCount++;
                numCounts.put(num, 0);
            } else {
                numCounts.put(num, nCount);
            }
        }

        int op1TotalCount = 0;
        for (int nCount : numCounts.values()) {
            if (nCount == 0) { // that number was already counted
                continue;
            }

            if (nCount == FIRST_OCCURRENCE_NO_OP) { // there is number that was seen only once.
//                return -1;
            }

            if (nCount == 1) { //
                op2TotalCount--;
                op1TotalCount +=2;
            } else if (nCount == 2 || nCount == FIRST_OCCURRENCE_1OP) {
                op1TotalCount++;
            }
        }

        return op1TotalCount + op2TotalCount;
    }
}