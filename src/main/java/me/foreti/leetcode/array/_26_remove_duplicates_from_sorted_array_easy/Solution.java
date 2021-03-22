package me.foreti.leetcode.array._26_remove_duplicates_from_sorted_array_easy;

import me.foreti.leetcode.util.JsonUtils;

/**
 * Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Given nums = [1,1,2],
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Given nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.
 * <p>
 * It doesn't matter what values are set beyond the returned length.
 * <p>
 * Clarification:
 * <p>
 * Confused why the returned value is an integer but your answer is an array?
 * <p>
 * Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.
 * <p>
 * Internally you can think of this:
 * <p>
 * <p>
 * // nums is passed in by reference. (i.e., without making a copy)
 * int len = removeElement(nums, val);
 * <p>
 * // any modification to nums in your function would be known by the caller.
 * // using the length returned by your function, it prints the first len elements.
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 * @author fakeyanss
 * create date: 2020/9/9
 */
public class Solution {

    /**
     * 审题，需要遍历数组，返回一个最终长度len，并保证前len个元素即最终结果。
     * 空间负责度O(1)，不能创建新数组，直接将后面不重复的元素移动到前面，返回最终元素个数。
     */
    public int solution(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int last = 0, finder = 0;
        while (last < nums.length - 1) {
            while (nums[finder] == nums[last]) {
                finder++;
                if (finder == nums.length) {
                    return last + 1;
                }
            }
            nums[++last] = nums[finder];
        }
        return last + 1;
    }

    /**
     * 解法相同，更易读
     */
    public int solution2(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        Solution removeDuplicates = new Solution();
        JsonUtils.printJsonPrettyString(removeDuplicates.solution(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
        JsonUtils.printJsonPrettyString(removeDuplicates.solution2(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
    }

}
