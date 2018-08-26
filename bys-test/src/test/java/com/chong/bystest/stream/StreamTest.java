package com.chong.bystest.stream;

import com.chong.bystest.lambda.Employee;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
public class StreamTest {


    @Test
    public void  test01(){

        ArrayList<Employee> employees = Lists.newArrayList();

        employees.add(new Employee("张三", 18));
        employees.add(new Employee("李四", 32));
        employees.add(new Employee("王五", 45));
        employees.add(new Employee("赵六", 21));
        employees.add(new Employee("田七", 32));
        employees.add(new Employee("宋八", 19));


        employees.stream()
                .filter(e -> e.getAge()>20)
                .sorted((e1,e2) ->{
                    if(e1.getAge()==e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }
                    return Integer.compare(e1.getAge(),e2.getAge());
                })
                .forEach(System.out::println);

    }


    @Test
    public void test02(){


        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> collect = integers.stream().map(i -> i * i).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


    @Test
    public void test03(){

        ArrayList<Employee> employees = Lists.newArrayList();

        employees.add(new Employee("张三", 18));
        employees.add(new Employee("李四", 32));
        employees.add(new Employee("王五", 45));
        employees.add(new Employee("赵六", 21));
        employees.add(new Employee("田七", 32));
        employees.add(new Employee("宋八", 19));


        Optional<Integer> reduce = employees.parallelStream()
                .map(e -> 1)
                .reduce(Integer::sum);
        log.info("统计：{}",reduce.get());

    }


    @Test
    public void testLocalDateTime() {

        LocalDateTime now = LocalDateTime.now();

        log.info("{}", now);

    }

}
