package me.foreti.leetcode.tree._105_construct_binary_tree_from_preorder_and_inorder_traversal_medium;

import java.util.HashMap;
import java.util.Map;

import me.foreti.leetcode.tree.base.TreeNode;

public class Solution {

    private Map<Integer, Integer> inOrderMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inOrderMap.put(inorder[i], i);
        }

        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // base case
        if (preStart > preEnd) {
            return null;
        }

        // 前序，定义根节点
        int rootVal = preorder[preStart];
        // 查找根节点在中序数组的位置
        int index = inOrderMap.get(rootVal);

        // 左子树长度
        int leftSize = index - inStart;

        TreeNode root = new TreeNode(rootVal);

        root.left = build(preorder, preStart + 1, preStart + leftSize, inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, inorder, index + 1, inEnd);

        return root;
    }

}
