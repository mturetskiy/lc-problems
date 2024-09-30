package com.mtur.lc;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class TasksQueue {
    public int leastInterval(char[] tasks, int n) {
        // we can optimize memory by calculating max length of contigous seq beforehand:

        char[] queue = new char[tasks.length];
        Map<Character, Integer> nextCoolPosMap = new HashMap<>(tasks.length); // next pos to insert accorfingly to cooling
        int nextFreePos = 0;
        int maxPos = 0;

        for (char ch: tasks) {
            int nextCoolPos = nextCoolPosMap.getOrDefault(ch, 0);
            int nextPos = Math.max(nextCoolPos, nextFreePos);
            maxPos = Math.max(maxPos, nextPos);

            if (queue.length <= nextPos) { // growth
                char[] tmp = new char[queue.length + 2*n];
                System.arraycopy(queue, 0, tmp, 0, queue.length);
                queue = tmp;
            }
            queue[nextPos] = ch;
            nextCoolPosMap.put(ch, nextCoolPos + n);

            do {
                nextFreePos++;
            } while(nextFreePos < queue.length && queue[nextFreePos] > 0);
        }

        return maxPos;
    }
}