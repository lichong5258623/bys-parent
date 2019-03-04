package com.chong.bystest.java;

import org.junit.Test;

import java.lang.reflect.Field;

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


    @Test
    public void testmain(){
        int [] nums = {2};
        int i = removeElement(nums, 3);
        System.out.println(i);
        for (int num:nums){
            System.out.print(num);
        }
    }

    public int removeElement(int[] nums, int val) {

        if(nums.length<1){
            return 0;
        }
        int arrayLast = nums.length-1;
        for(int i =0;i<=arrayLast;i++){
            if(nums[arrayLast]==val&&arrayLast>0){
                arrayLast--;
            }
            if(nums[i]==val){
                nums[i] = nums[arrayLast];
                arrayLast--;
            }
        }

        return arrayLast;
    }

    @Test
    public void testLong(){
        Long a = 60*1000L;
        System.out.println(a.intValue());
    }

    @Test
    public void testLongMaxValue(){

        System.out.println(Long.MAX_VALUE);
    }

    @Test
    public void testClassLoad() throws Exception {
        Class<?> clazz = Class.forName("com.chong.bystest.java.User");
        User user = (User)clazz.newInstance();

        Field name = clazz.getDeclaredField("name");
        Field id = clazz.getDeclaredField("id");
        name.setAccessible(true);
        name.set(user,"aaaaa" );
        id.setAccessible(true);
        System.out.println(user.getName());
    }

}
