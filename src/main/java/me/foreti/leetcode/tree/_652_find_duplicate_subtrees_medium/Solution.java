package me.foreti.leetcode.tree._652_find_duplicate_subtrees_medium;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import me.foreti.leetcode.tree.base.TreeNode;

public class Solution {

    Map<String, Integer> memo = new HashMap<>();
    LinkedList<TreeNode> res = new LinkedList<>();
    
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    private String traverse(TreeNode root) {
        if (null == root) {
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right + "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);

        if (1 == freq) {
            res.add(root);
        }

        memo.put(subTree, freq + 1);

        return subTree;
    }

}
