package me.foreti.leetcode.tree.base;

/* Definition for a binary tree node. */
public class TreeNode {

    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static void print(TreeNode node) {
        if (null == node) {
            return;
        }
        System.out.print(node.val + " ");
        print(node.left);
        print(node.right);
    }

}
