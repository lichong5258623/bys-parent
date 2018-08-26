package com.chong.bystest.guava;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
