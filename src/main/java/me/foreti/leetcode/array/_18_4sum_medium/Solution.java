package me.foreti.leetcode.array._18_4sum_medium;

import me.foreti.leetcode.util.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate quadruplets.
 * <p>
 * Example:
 * <p>
 * <p>
 * Given array nums = [1, 0, -1, 0, -2, 2], and target = 0.
 * <p>
 * A solution set is:
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author fakeyanss
 * create date: 2020/9/9
 */
public class Solution {

    /**
     * 思路和3SUM一样，枚举出现重复数字的情况，然后计算不重复的数字值
     */
    public List<List<Integer>> solution(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Integer> counter = new HashMap<>();

        for (int num : nums) {
            int count = Optional.ofNullable(counter.get(num)).orElse(0);
            counter.put(num, ++count);
        }

        Integer[] uniqNums = new Integer[counter.size()];
        counter.keySet().toArray(uniqNums);
        Arrays.sort(uniqNums);

        for (int i = 0; i < uniqNums.length; i++) {
            if (uniqNums[i] * 4 == target && counter.get(uniqNums[i]) >= 4) {
                res.add(Arrays.asList(uniqNums[i], uniqNums[i], uniqNums[i], uniqNums[i]));
            }
            for (int j = i + 1; j < uniqNums.length; j++) {
                if (uniqNums[i] * 3 + uniqNums[j] == target && counter.get(uniqNums[i]) >= 3) {
                    res.add(Arrays.asList(uniqNums[i], uniqNums[i], uniqNums[i], uniqNums[j]));
                }
                if (uniqNums[i] * 2 + uniqNums[j] * 2 == target && counter.get(uniqNums[i]) >= 2
                        && counter.get(uniqNums[j]) >= 2) {
                    res.add(Arrays.asList(uniqNums[i], uniqNums[i], uniqNums[j], uniqNums[j]));
                }
                if (uniqNums[i] + uniqNums[j] * 3 == target && counter.get(uniqNums[j]) >= 3) {
                    res.add(Arrays.asList(uniqNums[i], uniqNums[j], uniqNums[j], uniqNums[j]));
                }
                for (int k = j + 1; k < uniqNums.length; k++) {
                    if (uniqNums[i] * 2 + uniqNums[j] + uniqNums[k] == target && counter.get(uniqNums[i]) >= 2) {
                        res.add(Arrays.asList(uniqNums[i], uniqNums[i], uniqNums[j], uniqNums[k]));
                    }
                    if (uniqNums[i] + uniqNums[j] * 2 + uniqNums[k] == target && counter.get(uniqNums[j]) >= 2) {
                        res.add(Arrays.asList(uniqNums[i], uniqNums[j], uniqNums[j], uniqNums[k]));
                    }
                    if (uniqNums[i] + uniqNums[j] + uniqNums[k] * 2 == target && counter.get(uniqNums[k]) >= 2) {
                        res.add(Arrays.asList(uniqNums[i], uniqNums[j], uniqNums[k], uniqNums[k]));
                    }
                    int c = target - uniqNums[i] - uniqNums[j] - uniqNums[k];
                    if (c > uniqNums[k] && counter.containsKey(c)) {
                        res.add(Arrays.asList(uniqNums[i], uniqNums[j], uniqNums[k], c));
                    }
                }
            }
        }
        return res;
    }

    /**
     * 递归的思路，当nSum的n愈来愈大，暴力枚举已经无法满足。
     */
    public List<List<Integer>> solution2(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    public List<List<Integer>> kSum(int[] nums, int target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (start == nums.length || nums[start] * k > target || target > nums[nums.length - 1] * k)
            // 找不到符合的结果
            return res;
        if (k == 2)
            return twoSum(nums, target, start);
        for (int i = start; i < nums.length; ++i)
            if (i == start || nums[i - 1] != nums[i]) // 跳过重复节点
                for (List<Integer> set : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(Collections.singletonList(nums[i]));
                    res.get(res.size() - 1).addAll(set);
                }
        return res;
    }

    // 仍然是双指针从两头向中间遍历
    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int lo = start, hi = nums.length - 1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            // 审题"The solution set must not contain duplicate quadruplets."
            if (sum < target || (lo > start && nums[lo] == nums[lo - 1])) // skip重复的lo数字
                ++lo;
            else if (sum > target || (hi < nums.length - 1 && nums[hi] == nums[hi + 1])) // skip重复的hi数字
                --hi;
            else
                res.add(Arrays.asList(nums[lo++], nums[hi--]));
        }
        return res;
    }

    public static void main(String[] args) {
        Solution fourSum = new Solution();
        JsonUtils.printJsonPrettyString(fourSum.solution(new int[]{1, 0, -1, 0, -2, 2}, 0));
        JsonUtils.printJsonPrettyString(fourSum.solution2(new int[]{1, 0, -1, 0, -2, 2}, 0));
    }

}
