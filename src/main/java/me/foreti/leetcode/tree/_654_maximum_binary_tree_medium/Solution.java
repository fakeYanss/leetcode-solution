package me.foreti.leetcode.tree._654_maximum_binary_tree_medium;

import me.foreti.leetcode.tree.base.TreeNode;

public class Solution {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length -1);
    }

    private TreeNode build(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 找到数组最大值
        int index = -1, max = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (max < nums[i]) {
                index = i;
                max = nums[i];
            }
        }

        TreeNode root = new TreeNode(max);

        // 递归构造左右子树
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);

        return root;
    }

}
