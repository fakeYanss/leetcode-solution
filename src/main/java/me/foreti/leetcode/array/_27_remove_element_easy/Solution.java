package me.foreti.leetcode.array._27_remove_element_easy;

import me.foreti.leetcode.util.JsonUtils;

/**
 * Given an array nums and a value val, remove all instances of that value in-place and return the new length.
 * <p>
 * Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
 * <p>
 * The order of elements can be changed. It doesn’t matter what you leave beyond the new length.
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Given nums = [3,2,2,3], val = 3,
 * <p>
 * Your function should return length = 2, with the first two elements of nums being 2.
 * <p>
 * It doesn't matter what you leave beyond the returned length.
 * <p>
 * Example 2:
 * <p>
 * <p>
 * Given nums = [0,1,2,2,3,0,4,2], val = 2,
 * <p>
 * Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
 * <p>
 * Note that the order of those five elements can be arbitrary.
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

    public int solution(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                if (i != j) {
                    int temp = nums[j];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                i++;
            }
        }
        return i;
    }

    public int solution2(int[] nums, int val) {
        if (nums.length == 0) {
            return 0;
        }
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }
        return i;
    }

    public int solution3(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while (i < n) {
            if (nums[i] == val) {
                nums[i] = nums[n - 1];
                // reduce array size by one
                n--;
            } else {
                i++;
            }
        }
        return n;
    }

    public static void main(String[] args) {
        Solution removeElement = new Solution();
        JsonUtils.printJsonPrettyString(removeElement.solution(new int[]{3,2,2,3}, 3));
        JsonUtils.printJsonPrettyString(removeElement.solution(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
        JsonUtils.printJsonPrettyString(removeElement.solution2(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
        JsonUtils.printJsonPrettyString(removeElement.solution3(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2));
    }

}
