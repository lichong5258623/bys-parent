package com.chong.bys.bystest.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;

public class MyCallable implements Callable<List<Integer>> {
    @Override
    public List<Integer> call() throws Exception {

        LinkedList<Integer> integers = new LinkedList<>();

        for (int i=0;i<100;i++){
            integers.add(i);
        }
        return integers;
    }
}
