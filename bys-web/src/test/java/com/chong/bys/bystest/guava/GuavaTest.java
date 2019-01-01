package com.chong.bys.bystest.guava;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;

@Slf4j
public class GuavaTest {



    @Test
    public void test1(){


        ConcurrentMap<String, Object> map = Maps.newConcurrentMap();

        HashMap<String, Object> hashMap = Maps.newHashMap();

        TreeMap<Comparable, Object> comparableObjectTreeMap = Maps.newTreeMap();

        ArrayList<String> list = Lists.newArrayList();
        String a ="a";
        list.add(a);

    }

    @Test
    public void test01(){


    }


}
