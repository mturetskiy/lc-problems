package com.mtur.lc;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

@Slf4j
public class RandomTest {
    public static class ListNode {
        public int val;

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    @Test
    void name() {
        TreeSet<ListNode> highNodes = new TreeSet<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                int compare = Integer.compare(o1.val, o2.val);
                if (compare == 0) {
//                    return -1;
                    return 0;
                }

                return compare;
            }
        });
        ListNode n1 = new ListNode(5);
        highNodes.add(n1);
        highNodes.add(new ListNode(5));
        highNodes.add(new ListNode(1));
        highNodes.add(new ListNode(7));

        log.info("Nodes: " + Arrays.toString(highNodes.toArray()));


        highNodes.remove(n1);
        log.info("Nodes: " + Arrays.toString(highNodes.toArray()));
    }
}