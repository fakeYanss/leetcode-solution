package me.foreti.leetcode.array._40_combination_sum_ii_medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import me.foreti.leetcode.util.JsonUtils;

/**
 * Given a collection of candidate numbers (candidates) and a target number
 * (target), find all unique combinations in candidates where the candidate
 * numbers sum to target.
 *
 * Each number in candidates may only be used once in the combination.
 *
 * Note: The solution set must not contain duplicate combinations.
 *
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8,
 * Output: 
 * [ 
 *   [1,1,6], 
 *   [1,2,5],
 *   [1,7], 
 *   [2,6]
 * ]
 * 
 * Example 2:
 *
 * Input: candidates = [2,5,2,1,2], target = 5,
 * Output: 
 * [ 
 *   [1,2,2],
 *   [5]
 * ]
 *
 * Constraints:
 *
 * 1 <= candidates.length <= 100 1 <= candidates[i] <= 50 1 <= target <= 30
 */
public class Solution {

    /**
     * 题目要求出总和为 sum 的所有组合，组合需要去重。这一题是第 39 题的加强版，第 39
     * 题中元素可以重复利用(重复元素可无限次使用)，这一题中元素只能有限次数的利用，因为存在重复元素，并且每个元素只能用一次(重复元素只能使用有限次)
     * 这一题和第 47 题类似，只不过元素可以反复使用。
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (0 == candidates.length) {
            return res;
        }
        LinkedList<Integer> c = new LinkedList<>();
        Arrays.sort(candidates);

        dfs(candidates, target, 0, c, res);
        return res;
    }

    private void dfs(int[] candidates, int target, int index, LinkedList<Integer> c,
            List<List<Integer>> res) {
        if (0 == target) {
            res.add(new ArrayList<>(c));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (target >= candidates[i]) {
                c.add(candidates[i]);
                dfs(candidates, target - candidates[i], i + 1, c, res);
                c.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        int[] candidates = new int[] { 10, 1, 2, 7, 6, 1, 5 };
        int target = 8;
        Solution solution = new Solution();
        JsonUtils.printJsonPrettyString(solution.combinationSum2(candidates, target));
    }

}
