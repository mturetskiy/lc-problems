package com.mtur.lc;

import java.util.Arrays;

public class EmptyArrayMinOpsOpt {
    public int minOperations(int[] nums) {
        Arrays.sort(nums);

        int opsCount = 0;
        int i = 0;
        while(i < nums.length){
            int next = i + 1;

            while(next < nums.length && nums[next] == nums[i]) {
                next++;
            }

            int numCount = next - i;
            if(numCount == 1) return -1;

            opsCount += getNumOpsCount(numCount);

            i = next;
        }

        return opsCount;
    }

    private static int getNumOpsCount(int numCount) {
        int res = numCount / 3;
        if(numCount % 3 != 0) { // 1st op (+ 2nd op was already counted)
            return ++ res;
        }

        return res;
    }
}