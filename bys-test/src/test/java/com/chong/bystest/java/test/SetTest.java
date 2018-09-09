package com.chong.bystest.java.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @author lichong
 * 2018/9/9 21:29
 * @version 1
 * @since 1.0
 */
public class SetTest {


    @Test
    public void test01(){

        List<String> strings = new ArrayList<>();
        for (int i = 0;i<10;i++){
            strings.add("string"+i);
        }
        for (int i = 0;i<10;i++){
            strings.add("string"+i);
        }

        HashSet<String> strings1 = new HashSet<>(strings);

        System.out.println(strings.size());
        System.out.println(strings1.size());


    }

}
