package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class LongIncrSubSeq {
    int iters = 0;
    public int lengthOfLIS(int[] nums) {
        iters = 0;
        int[] lengths = new int[nums.length];
        Arrays.fill(lengths, -1);
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            int curr = findDepth(i, nums, lengths);
            max = Math.max(max, curr);
        }

        log.info("Iters for {}: {}", nums.length, iters);
        log.info("Length: {}", lengths);
        return max + 1;
    }

    private int findDepth(int start, int[] nums, int[] lengths) {
        if (lengths[start] != -1) {
            return lengths[start];
        }

        if (start >= nums.length - 1) {
            lengths[start] = 0;
            return 0;
        }

        int max = 0;
        for (int i = start + 1; i < nums.length; i++) {
            iters++;
            int curr = 0;
            if (nums[i] > nums[start]) {
                int depth = findDepth(i, nums, lengths);
                lengths[i] = depth;
                curr += depth + 1;
            }

            max = Math.max(max, curr);
        }

        lengths[start] = max;
        return max;
    }
}