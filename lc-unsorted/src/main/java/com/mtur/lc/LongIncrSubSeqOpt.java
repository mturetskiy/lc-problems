package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LongIncrSubSeqOpt {
    public int lengthOfLIS(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            int curr = findDepth(i, nums, "");
            log.info("For {}  length resolved as: {}.", i, curr);
            max = Math.max(max, curr);
        }
        return max + 1;
    }

    private int findDepth(int start, int[] nums, String prefix) {
        if (start >= nums.length - 1) {
            return 0;
        }

        List<String> pairs = new ArrayList<>();
        int max = 0;
        for (int i = start + 1; i < nums.length; i++) {
            int curr = 0;
            if (nums[i] > nums[start]) {
                String pair = nums[start] + "-" + nums[i];
                pairs.add(pair);
                log.info("{}pair for: {} => {}", prefix, nums[start], pair);
                curr += findDepth(i, nums, prefix + "   ") + 1;
            }

            max = Math.max(max, curr);
        }


        return max;
    }
}