package com.chong.bystest.lambda;

@FunctionalInterface
public interface MyFun2<T,R> {

    public R doSoming(T t1, T t2);

}
