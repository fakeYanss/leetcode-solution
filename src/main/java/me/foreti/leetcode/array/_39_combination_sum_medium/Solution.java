package me.foreti.leetcode.array._39_combination_sum_medium;

import me.foreti.leetcode.util.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
 * <p>
 * The same repeated number may be chosen from candidates unlimited number of times.
 * <p>
 * Note:
 * <p>
 * All numbers (including target) will be positive integers.
 * The solution set must not contain duplicate combinations.
 * Example 1:
 * <p>
 * Input: candidates = [2,3,6,7], target = 7,
 * A solution set is:
 * [
 * [7],
 * [2,2,3]
 * ]
 * Example 2:
 * <p>
 * Input: candidates = [2,3,5], target = 8,
 * A solution set is:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 *
 * @author fakeyanss
 * create date: 2020/9/10
 */
public class Solution {

    /**
     * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
     * <p>
     * candidates 中的数字可以无限制重复被选取。
     * <p>
     * 题目要求出总和为 sum 的所有组合，组合需要去重。
     * <p>
     * 这一题和第 47 题类似，只不过元素可以反复使用。
     * <p>
     * backtrack
     */
    public List<List<Integer>> solution(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        this.backtrack(target, comb, 0, candidates, results);
        return results;
    }

    private void backtrack(
            int remain,
            LinkedList<Integer> comb,
            int start, int[] candidates,
            List<List<Integer>> results) {
        if (remain == 0) {
            // make a deep copy of the current combination
            results.add(new ArrayList<>(comb));
            return;
        } else if (remain < 0) {
            // exceed the scope, stop exploration.
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            // add the number into the combination
            comb.add(candidates[i]);
            this.backtrack(remain - candidates[i], comb, i, candidates, results);
            // backtrack, remove the number from the combination
            comb.removeLast(); // amazing, you can add "System.out.println(comb);" in the 1st line and you'll see.
        }
    }

    // DP
    public List<List<Integer>> solution2(int[] candidates, int target) {
        // sort candidates to try them in asc order
        Arrays.sort(candidates);
        // dp[t] stores all combinations that add up to t
        // List<List<Integer>>[] dp = new ArrayList[target + 1];
        List<List<List<Integer>>> dp = new ArrayList<>(target + 1);

        // build up dp
        for (int t = 0; t <= target; t++) {
            // initialize
            dp.add(t, new ArrayList<>());
            // initialize
            List<List<Integer>> combList = new ArrayList<>();

            // for each t, find possible combinations
            for (int j = 0; j < candidates.length && candidates[j] <= t; j++) {
                if (candidates[j] == t) {
                    combList.add(Collections.singletonList(candidates[j])); // itself can form a list
                } else {
                    for (List<Integer> prevlist : dp.get(t - candidates[j])) { // here use our dp definition
                        // i thought it makes more sense to compare with the last element
                        // only add to list when the candidates[j] >= the last element
                        // so the list remains ascending order, can prevent duplicate (ex. has [2 3 3], no [3 2 3])
                        // equal is needed since we can choose the same element many times
                        if (candidates[j] >= prevlist.get(prevlist.size() - 1)) {
                            List<Integer> temp = new ArrayList<>(prevlist); // temp is needed since
                            temp.add(candidates[j]); // cannot edit prevlist inside 4eeach looop
                            combList.add(temp);
                        }
                    }
                }
            }
            dp.add(t, combList);
            // JsonUtils.printJsonPrettyString(dp);
        }
        return dp.get(target);
    }

    public static void main(String[] args) {
        Solution combinationSum = new Solution();
        // JsonUtils.printJsonPrettyString(combinationSum.solution(new int[]{2, 3, 5}, 8));
        JsonUtils.printJsonPrettyString(combinationSum.solution2(new int[]{2, 3, 5}, 8));
    }

}
