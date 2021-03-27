package me.foreti.leetcode.linkedlist._61_rotate_list_medium;

import me.foreti.leetcode.linkedlist.base.ListNode;

public class Solution {

    public ListNode rotateRight(ListNode head, int k) {
        if (null == head || null == head.next || 0 == k) {
            return head;
        }
        int len = 1;
        ListNode iter = head;
        while (null != iter.next) {
            len++;
            iter = iter.next;
        }
        // optimize
        int steps = len - k % len;
        if (steps == len) {
            return head;
        }

        // 上环
        iter.next = head;
        while (steps-- > 0) {
            iter = iter.next;
        }
        ListNode res = iter.next;
        // 取环
        iter.next = null;
        return res;
    }

}
