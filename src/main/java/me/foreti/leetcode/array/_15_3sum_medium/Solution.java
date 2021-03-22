package me.foreti.leetcode.array._15_3sum_medium;

import me.foreti.leetcode.util.JsonUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * <p>
 * Note:
 * <p>
 * The solution set must not contain duplicate triplets.
 * <p>
 * Example:
 * <p>
 * <p>
 * Given array nums = [-1, 0, 1, 2, -1, -4],
 * <p>
 * A solution set is:
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * @author fakeyanss
 * create date: 2020/9/8
 */
public class Solution {

    /**
     * 给定一个数组，要求在这个数组中找出 3 个数之和为 0 的所有组合。
     * <p>
     * 先排序数组，再遍历
     * 三个指针i，j，k
     * i from 0 to n-3
     * j from i+1 to n-2
     * k from i+2 to n-1
     */
    public List<List<Integer>> solution(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        Set<List<Integer>> set = new HashSet<>();
        for (int i = 0; i < nums.length - 3; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum == 0) {
                    set.add(Arrays.asList(nums[i], nums[j++], nums[k--]));
                } else if (sum > 0) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return new ArrayList<>(set);
    }

    /**
     * 更暴力的解法，不依赖set，可能在某些语言中更普适；在leetcode中运行更快。
     */
    public List<List<Integer>> solution2(int[] nums) {
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
            if (uniqNums[i] * 3 == 0 && counter.get(uniqNums[i]) >= 3) {
                res.add(Arrays.asList(uniqNums[i], uniqNums[i], uniqNums[i]));
            }
            for (int j = i + 1; j < uniqNums.length; j++) {
                if (uniqNums[i] * 2 + uniqNums[j] == 0 && counter.get(uniqNums[i]) >= 2) {
                    res.add(Arrays.asList(uniqNums[i], uniqNums[i], uniqNums[j]));
                }
                if (uniqNums[j] * 2 + uniqNums[i] == 0 && counter.get(uniqNums[j]) >= 2) {
                    res.add(Arrays.asList(uniqNums[i], uniqNums[j], uniqNums[j]));
                }
                int c = -uniqNums[i] - uniqNums[j];
                if (c > uniqNums[j] && counter.containsKey(c)) {
                    res.add(Arrays.asList(uniqNums[i], uniqNums[j], c));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution threeSum = new Solution();
        JsonUtils.printJsonPrettyString(threeSum.solution(new int[]{-1, 0, 1, 2, -1, -4}));
        JsonUtils.printJsonPrettyString(threeSum.solution2(new int[]{-1, 0, 1, 2, -1, -4}));
    }

}
