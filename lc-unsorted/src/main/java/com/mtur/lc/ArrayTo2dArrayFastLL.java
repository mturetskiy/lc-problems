package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ArrayTo2dArrayFastLL {
    public List<List<Integer>> findMatrix(int[] nums) {
        List<List<Integer>> matrix = new LinkedList<>();
        Map<Integer, Integer> numRowNums =  new HashMap<>(nums.length, 1.0f);

        for (int num : nums) {
            Integer lastRowNum = numRowNums.computeIfAbsent(num, n -> -1);
            int rowToAdd = ++lastRowNum;
            List<Integer> row = getNthRow(matrix, rowToAdd);
            row.add(num);
            numRowNums.put(num, rowToAdd);
        }

        return matrix;
    }

    private List<Integer> getNthRow(List<List<Integer>> matrix, int rowNum) {
        if (rowNum < matrix.size()) {
            return matrix.get(rowNum);
        }

        List<Integer> row = new LinkedList<>();
        matrix.add(row);
        return row;
    }
}
