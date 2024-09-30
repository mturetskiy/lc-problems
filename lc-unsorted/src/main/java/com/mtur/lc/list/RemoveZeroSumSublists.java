package com.mtur.lc.list;

import com.mtur.lc.ds.ListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Key ideas:
 *  - we can calc sum  by adding element by element. If you look at **identical sum** values of this sum sequence - they mean that between those values we actually have consecutive elements sum of which is zero (that's why sum become to some value back). So, we can remove elements between 1st (exclusive) and last (inclusive) values.
 *  - HashMap with sum as a key helps to track (and **update**, when second sum with the same value appears) the link to the **next node** (which we need to use for remove operation.
 *  - We also need to add **special key with 0** at the beginning with a link to the head (preHead actually), so when first elements eventually go to zero (e.g. 1, 2, -3, 7 ..), we can change head (link to head) to 7.
 *  - PreHead helps us to keep track and update the head. It is required because when we remove elements between identical summs, we need to preserve 1st one, which sometimes is head, so we need something to link to the head (to replace it).
 *  - In second look, when we **remove** elements (by linking **curr.next** with  **sumNode.next**) we still can use calculated sum value, even after skipping removed elements. This works  because **sums stays the same** with them, or without them.
 */

public class RemoveZeroSumSublists {

    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> sumMap = new HashMap<>();

        ListNode currentNode = head;
        ListNode preHead = new ListNode(0, head);
        sumMap.put(0, preHead);
        int sum = 0;

        while (currentNode != null) {
            sum += currentNode.val;
            sumMap.put(sum, currentNode);
            currentNode = currentNode.next;
        }

        currentNode = preHead;
        sum = 0;
        while (currentNode != null) {
            sum += currentNode.val;
            ListNode sumNode = sumMap.get(sum);

            currentNode.next = sumNode.next;
            currentNode = currentNode.next;
        }


        return preHead.next;
    }
}
