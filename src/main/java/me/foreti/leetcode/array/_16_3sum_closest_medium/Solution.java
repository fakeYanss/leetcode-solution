package me.foreti.leetcode.array._16_3sum_closest_medium;

import me.foreti.leetcode.util.JsonUtils;

import java.util.Arrays;

/**
 * Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * <p>
 * <p>
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * @author fakeyanss
 * create date: 2020/9/9
 */
public class Solution {

    /**
     * 暴力解法
     */
    public int solution(int[] nums, int target) {
        int len = nums.length, diff = Integer.MAX_VALUE, res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int temp = Math.abs(sum - target);
                    if (temp < diff) {
                        diff = temp;
                        res = sum;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 两个指针从头尾向中间遍历
     */
    public int solution2(int[] nums, int target) {
        int len = nums.length, diff = Integer.MAX_VALUE, res = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            if (i > 1 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1, k = len - 1; j < k;) {
                int sum = nums[i] + nums[j] + nums[k];
                int temp = Math.abs(sum - target);
                if (temp < diff) {
                    diff = temp;
                    res = sum;
                }
                if (sum == target) {
                    return sum;
                } else if (sum > target) {
                    k--;
                } else {
                    j++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution threeSumClosest = new Solution();
        JsonUtils.printJsonPrettyString(threeSumClosest.solution(new int[]{-1, 2, 1, -4}, 1));
        JsonUtils.printJsonPrettyString(threeSumClosest.solution2(new int[]{-1, 2, 1, -4}, 1));
    }

}
