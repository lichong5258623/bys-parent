package com.chong.bystest.lambda;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Consumer;

@Slf4j
public class LambdaTest {


    @Test
    public void test1() {

        log.info("good");

    }


    /**
     * 第一种格式:无参数无返回值
     * 例如runnable 接口
     * () -> System.out.println("Hello lambda !");
     */
    @Test
    public void test02() {

        ArrayList<Integer> list = Lists.newArrayList();

        for (int i = 0; i < 100; i++) {

            list.add(i);
        }

        long count = list.stream().count();
        log.info("集合总数：{}", count);

        list.stream().filter(s -> s > 20).map(i -> {
            return i.toString();
        }).forEach(i -> log.info("计数：{}", i));

        Runnable r1 = new Runnable() {

            @Override
            public void run() {

                System.out.println("Hello World !");

            }
        };

        Runnable r2 = () -> System.out.println("Hello lambda !");

        r1.run();
        r2.run();

    }


    /**
     * 第二种：一个参数，无返回值
     * 例如consumer 消费者
     */
    @Test
    public void test03() {


        Consumer<String> consumer = (s) -> System.out.println(s);

        consumer.accept("你好消费者");

    }

    /**
     * 第三种：两个参数，有返回值
     * <p>
     * 例如比较器
     */
    @Test
    public void test04() {

        Comparator<Integer> comparator = (x, y) -> {
            return x > y ? x : y;
        };
        int compare = comparator.compare(1, 2);
        log.info("比较后返回的信息{}", compare);

    }


    @Test
    public void test05() {

        ArrayList<Employee> employees = Lists.newArrayList();

        employees.add(new Employee("张三", 18));
        employees.add(new Employee("李四", 32));
        employees.add(new Employee("王五", 45));
        employees.add(new Employee("赵六", 21));
        employees.add(new Employee("田七", 32));
        employees.add(new Employee("宋八", 19));


        employees.sort((e1, e2) -> {

            if (e1.getAge().equals(e2.getAge())) {

                return e1.getName().compareTo(e2.getName());
            } else {

                return Integer.compare(e1.getAge(), e2.getAge());
            }
        });


        employees.forEach(System.out::println);

    }

    @Test
    public void test06() {

        String str = myfunTest("asfagtwgerfFFF", (s) -> s.toUpperCase());

        log.info("自定义函数返回值1-- {} -- ", str);

        String str2 = myfunTest("asdfasdf", s -> s.substring(2, 4));

        log.info("自定义函数返回值2-- {} -- ", str2);
    }

    private String myfunTest(String str, MyFun myFun) {

        return myFun.getValue(str);

    }


    @Test
    public void test07() {

        Long sum = myfun2Test(32, 63, (l1, l2) -> l1 + l2);

        log.info("计算Long的值：{}", sum);

    }

    private Long myfun2Test(long l1, long l2, MyFun2<Long, Long> myFun2) {

        return myFun2.doSoming(l1, l2);
    }


}
