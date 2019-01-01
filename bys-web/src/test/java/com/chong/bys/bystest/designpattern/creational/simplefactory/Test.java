package com.chong.bys.bystest.designpattern.creational.simplefactory;

public class Test {


    @org.junit.Test
    public void test01() {

        Viedo java = new ViedoFactory().getViedo("java");

        java.produce();

    }


    @org.junit.Test
    public void test02(){
        String name = getClass().getName();
        System.out.println(name);
    }

}
