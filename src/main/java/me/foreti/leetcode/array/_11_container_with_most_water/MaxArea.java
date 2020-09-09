package me.foreti.leetcode.array._11_container_with_most_water;

import me.foreti.leetcode.array.util.JsonUtils;

/**
 *Given n non-negative integers a1, a2, …, an , where each represents a point at coordinate (i, ai). n vertical lines
 * are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis
 * forms a container, such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 *
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
 *
 * Example 1:
 *
 *
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 *
 * @author guichen01
 * create date: 2020/9/8
 */
public class MaxArea {

    /**
     * 给出一个非负整数数组 a1，a2，a3，…… an，每个整数标识一个竖立在坐标轴 x 位置的一堵高度为 ai 的墙，选择两堵墙，
     * 和 x 轴构成的容器可以容纳最多的水。
     *
     * 两端指针向中间移动，每次保存当前的最大面积，最终返回最大面积。
     */
    public int solution(int[] height) {
        int max = 0, start = 0, end = height.length - 1;
        while (start < end) {
            int width = end - start;
            int high;
            if (height[start] < height[end]) {
                high = height[start];
                start++;
            } else {
                high = height[end];
                end--;
            }

            max = Math.max(max, width * high);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxArea maxArea = new MaxArea();
        JsonUtils.printJsonPrettyString(maxArea.solution(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

}
