package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ArrayTo2dArrayOpt {
    public List<List<Integer>> findMatrix(int[] nums) {
        int[] numRowNums = new int[nums.length];
        List<List<Integer>> matrix = new ArrayList<>();


        for (int num : nums) {
            int rowNum = numRowNums[num];

            List<Integer> row;
            if (rowNum < matrix.size()) {
                row = matrix.get(rowNum);
            } else {
                row = new ArrayList<>();
                matrix.add(row);
            }

            row.add(num);
            numRowNums[num]++;
        }

        return matrix;
    }
}
