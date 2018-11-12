package com.chong.bystest.java.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

    @Test
    public void test02(){

        User2 user2 = User2.builder().address("中国").creatTime(new Date()).name("李崇").build();
        System.out.println(user2.toString());

    }

    @Test
    public void test03(){
        HashSet<String> set = new HashSet<>();
        ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();

        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("aaaaa");
        String s = stringThreadLocal.get();
        System.out.println(s);
    }


    @Test
    public void testListindex(){

        ArrayList<String> strings = new ArrayList<>();

        for(int i = 1;i<10;i++){
            strings.add("str"+i);
        }
    }



}
