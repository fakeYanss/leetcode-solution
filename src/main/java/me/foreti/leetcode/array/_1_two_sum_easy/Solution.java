package me.foreti.leetcode.array._1_two_sum_easy;

import me.foreti.leetcode.util.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 *
 * Example:
 *
 *
 * Given nums = [2, 7, 11, 15], target = 9,
 *
 * Because nums[0] + nums[1] = 2 + 7 = 9,
 * return [0, 1]
 *
 *  @author fakeyanss
 * create date: 2020/9/8
 */
public class Solution {

    /**
     * 顺序遍历数组，用map存数字和下标，key为数字，value为下标，每次从map获取当前数字的另一半数字，找不到则存入map。
     */
    public int[] solution(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int another = target - nums[i];
            if (map.containsKey(another)) {
                return new int[]{map.get(another), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        Solution twoSum = new Solution();
        JsonUtils.printJsonPrettyString(twoSum.solution(new int[]{2, 7, 11, 15}, 9));
        JsonUtils.printJsonPrettyString(twoSum.solution(new int[]{2, 7, 11, 15}, 13));
        JsonUtils.printJsonPrettyString(twoSum.solution(new int[]{2, 7, 11, 15}, 10));
    }

}
