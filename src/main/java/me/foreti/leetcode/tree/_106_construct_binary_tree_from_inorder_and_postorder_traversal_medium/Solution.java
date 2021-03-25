package me.foreti.leetcode.tree._106_construct_binary_tree_from_inorder_and_postorder_traversal_medium;

import java.util.HashMap;
import java.util.Map;

import me.foreti.leetcode.tree.base.TreeNode;

public class Solution {

    private Map<Integer, Integer> inorderMap = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    public TreeNode build(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        if (postStart > postEnd) {
            return null;
        }
        int rootVal = postorder[postEnd];
        int index = inorderMap.get(rootVal);
        int leftSize = index - inStart;

        TreeNode root = new TreeNode(rootVal);
        root.left = build(inorder, inStart, index - 1, postorder, postStart, postStart + leftSize - 1);
        root.right = build(inorder, index + 1, inEnd, postorder, postStart + leftSize, postEnd - 1);
        return root;
    }

}
