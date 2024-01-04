package com.mtur.lc;

import java.util.HashMap;
import java.util.Map;

public class EmptyArrayMinOps {
    public int minOperations(int[] nums) {
        Map<Integer, Integer> numCounts = new HashMap<>();

        for (int num : nums) {
            int nCount = numCounts.computeIfAbsent(num, n -> 0);
            numCounts.put(num, ++nCount);
        }

        int op1TotalCount = 0;
        int op2TotalCount = 0;
        for (int nCount : numCounts.values()) {
            if (nCount == 1) {
                return -1;
            }

            int op2Cnt = nCount / 3; // for 2nd op
            int op2Rem = nCount % 3; // reminder, need to 1st op

            if (op2Rem == 1) { // can use op2 one time less
                op2TotalCount += op2Cnt - 1;
                op1TotalCount += 2;
            } else if (op2Rem == 2) {
                op2TotalCount += op2Cnt;
                op1TotalCount++; // one 1st op is enough
            } else { // rem = 0, no 1st op needed
                op2TotalCount += op2Cnt;
            }
        }

        return op1TotalCount + op2TotalCount;
    }
}