package com.chong.bystest.jdk8;

import org.junit.Test;

import java.util.Optional;
import java.util.function.*;

/**
 *
 * 常用的函数式接口
 *
 * @author lichong
 */
public class LambdaTest {


    /**
     * Consumer:接受一个参数，不返回结果 （ps:消费者）
     */
    public void consumerTest(){
        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {

            }
        };

    }
    /**
     * supplier 不接受参数，直接返回一个结果（ps:生产者）
     */
    public void supplierTest(){

        Supplier supplier = new Supplier() {
            @Override
            public Object get() {
                return null;
            }
        };

    }
    /**
     * function 接受一个参数，返回一个结果
     * compose 方法：先执行传入的方法，在执行本方法
     * andThen 方法：先执行本方法在执行传入的方法
     */
    public void functionTest1(){

        Function function = new Function<String,String>() {

            @Override
            public String apply(String s) {
                return null;
            }
        };

    }

    /**
     * bifunction 接受两个个参数返回一个结果
     * andThen方法是先执行本方法，然后将本方法的结果应用于传入的function方法
     */
    public void bifunctionTest1(){
        BiFunction biFunction = new BiFunction() {
            @Override
            public Object apply(Object o, Object o2) {
                return null;
            }
        };
    }

    /**
     * prodicate 返回一个boolean类型
     * and: 与
     * or: 或
     * negate: 非
     * isEqual: 判断是否是传入的对象
     */
    public void prodicateTest(){
        Predicate predicate = new Predicate() {
            @Override
            public boolean test(Object o) {
                return false;
            }
        };
    }


    @Test
    public void optionalTest(){

        Optional.ofNullable("aaa").ifPresent(System.out::println);
    }


}
