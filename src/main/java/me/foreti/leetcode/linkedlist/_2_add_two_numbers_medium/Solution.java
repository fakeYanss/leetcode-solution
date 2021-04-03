package me.foreti.leetcode.linkedlist._2_add_two_numbers_medium;

import me.foreti.leetcode.linkedlist.base.ListNode;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean more = false;
        ListNode head = null, tail = null;
        while (l1 != null || l2 != null) {
            int a = l1 == null ? 0 : l1.val;
            int b = l2 == null ? 0 : l2.val;
            int val = a + b + (more ? 1 : 0);
            if (head == null) {
                head = tail = new ListNode(val % 10);
            } else {
                tail.next = new ListNode(val % 10);
                tail = tail.next;
            }
            more = val >= 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (more) {
            tail.next = new ListNode(1);
        }
        return head;
    }
}