package com.chong.bystest.java;

import org.junit.Test;

/**
 * 功能说明：
 *
 * @author : <a href="mailto:lichong@zjport.gov.cn">lichong</a>
 * @version : 1.0
 * @since 3.0
 */
public class JavaTest {


    @Test
    public void test01() {
//            [0,0,1,1,1,2,2,3,3,4],
        int nums[] = {0,0,1,1,1,2,2,3,3,4};
        int i = removeDuplicates(nums);
        System.out.println(i);
    }


    private int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int i = 0;
        for (int j = i; j < nums.length; j++) {
            if (nums[j] > nums[i]) {
                i++;
                nums[i] = nums[j];
            }
            for (int num : nums) {
                System.out.print(num);
            }
            System.out.println();
        }
        return i + 1;
    }

}
