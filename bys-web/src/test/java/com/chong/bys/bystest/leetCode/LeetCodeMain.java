package com.chong.bys.bystest.leetCode;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author lichong
 * 2018/11/5 17:36
 * @version 1
 * @since 1.0
 */
public class LeetCodeMain {

    @Test
    public void test01() {

        int target = 9;
        int[] nums = new int[]{2, 7, 11, 15};
        int[] ints = twoSum(nums, target);
        System.out.println(ints[0]+"和"+ints[1]);
    }

    //    给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
//    你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
//    示例:
//    给定 nums = [2, 7, 11, 15], target = 9
//    因为 nums[0] + nums[1] = 2 + 7 = 9
//    所以返回 [0, 1]
    private int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> integerMaps = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            int num = target - nums[i];
            if (integerMaps.containsKey(num)) {
                return new int[]{ integerMaps.get(num),i};
            }
            integerMaps.put(nums[i], i);
        }
        return null;
    }

}
