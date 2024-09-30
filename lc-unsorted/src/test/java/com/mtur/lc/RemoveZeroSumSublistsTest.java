package com.mtur.lc;

import com.mtur.lc.ds.ListNode;
import com.mtur.lc.list.RemoveZeroSumSublists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
class RemoveZeroSumSublistsTest {
    @Test
    void testCase1() {
        RemoveZeroSumSublists removeZeroSumSublists = new RemoveZeroSumSublists();

        ListNode head = toList(new int[]{1, 2, -3, 3, 1});
        log.info("Input: {}", toString(head));
        ListNode res = removeZeroSumSublists.removeZeroSumSublists(head);

        log.info("Result: {}", toString(res));
        assertEquals("[3, 1]", toString(res));
    }


    @Test
    void testCase3() {
        RemoveZeroSumSublists removeZeroSumSublists = new RemoveZeroSumSublists();

        ListNode head = toList(new int[]{1, 2, 3, -3, -2, 5, 4, 8, -4, -8, 7});
        log.info("Input: {}", toString(head));
        ListNode res = removeZeroSumSublists.removeZeroSumSublists(head);

        log.info("Result: {}", toString(res));
        assertEquals("[1, 5, 7]", toString(res));
    }

    ListNode toList(int[] data) {
        ListNode head = new ListNode(data[0]);
        ListNode curr = head;
        for (int i = 1; i < data.length; i++) {
            ListNode next = new ListNode(data[i]);
            curr.next = next;
            curr = curr.next;
        }
        return head;
    }

    public String toString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        ListNode curr = head;
        while (curr != null) {
            sb.append(curr.val);
            curr = curr.next;

            if (curr != null) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}