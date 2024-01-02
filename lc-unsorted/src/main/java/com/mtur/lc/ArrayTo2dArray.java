package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class ArrayTo2dArray {
    public List<List<Integer>> findMatrix(int[] nums) {
        LinkedList<Set<Integer>> res = new LinkedList<>();
        List<List<Integer>> resultList = new LinkedList<>();

        for (int num : nums) {
            boolean isAdded  = false;
            Iterator<Set<Integer>> resIter = res.iterator();
            while (!isAdded) {
                Set<Integer> curRow;
                if (resIter.hasNext()) {
                    curRow = resIter.next();
                } else {
                    curRow = new HashSet<>();

                    res.add(curRow);
                }

                if (curRow.add(num)) {
                    isAdded = true;
                }
            }
        }

        for (Set<Integer> row : res) {
            resultList.add(new ArrayList<>(row));
        }

        return resultList;
    }
}
