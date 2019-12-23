package com.example.spring;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author: Staro
 * @date: 2019/7/210:30
 * @Description:
 */
public class LambdaTest {

    /**
     * lambda表达式接口方法测试（有返回值测试）
     * 接口要求：接口中有且只能有一个抽象方法
     */
    @Test
    public void lambda() {
        MathOperation operation = (int a, int b) -> a + b;
        int operation1 = operation.operation(1, 3);
        System.out.println(operation1);
    }

    /**
     * lambda表达式接口方法测试（无返回值测试）
     * 接口要求：接口中有且只能有一个抽象方法
     */
    @Test
    public void testLam() {
        String salutation = "hello ";
        GreetingService greetingService = message -> System.out.println(salutation + message);
        greetingService.sayMessage("lambda!");
    }

    /**
     * lambda表达式接口方法测试（final测试）
     * 接口要求：接口中有且只能有一个抽象方法
     */
    @Test
    public void testFinal() {
        FinalTest finalTest = (a, b) -> a + b;
        System.out.println(finalTest.add(1, 3));
    }

    /**
     * 实战
     * lambda表达式接口方法测试（线程测试）
     */
    @Test
    public void testThread() throws InterruptedException {
        //原始方法
        Thread thread = new Thread(new MyThread());
        thread.start();
        Thread.sleep(5000);

        //lambda表达式 简化1
        new Thread(() -> System.out.println("thread 2")).start();
        Thread.sleep(5000);
        //lambda 表达式 简化2
        Process.process(() -> System.out.println("thread 3"));
        Thread.sleep(5000);
    }

    /**
     * 实战
     * lambda 排序
     */
    @Test
    public void testSort() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(1);
        list.add(6);
        list.add(2);
        list.add(4);
        //原始list排序
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        list.forEach(System.out::print);
        System.out.println();
        //使用lambda排序
        list.sort((o1, o2) -> o2 - o1);
        list.forEach(System.out::print);
    }

    /**
     * 实战
     * lambda 过滤
     */
    @Test
    public void testFilter(){
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(1);
        list.add(6);
        list.add(2);
        list.add(4);
        list.sort((o1,o2)->o1-o2);
        List<Integer> collect = list.stream().filter((s) -> s > 3 && s < 6).map(x->x*x).collect(Collectors.toList());
        collect.forEach(System.out::println);
        Integer integer = list.stream().filter((s) -> s > 3 && s < 6).map(x -> x * x).reduce((sum, x) -> sum + x).get();
        System.out.println(integer);
    }

    /**
     * 实战
     * lambda Predicate
     * Java 8添加了一个包，叫做 java.util.function。它包含了很多类，用来支持Java的函数式编程。其中一个便是Predicate，
     * Predicate接口非常适用于做过滤
     */
    @Test
    public void testPredicate(){
        List<String> list = Arrays.asList("Java","Python","scala","Shell","R");
        filterPredicate(list,x->x.contains("a"));
    }
    private static void filterPredicate(List<String>list, Predicate<String> condition){
        list.stream().filter(condition).forEach(System.out::println);
    }
/** **************************************************************接口************************************************* */
    /**
     * 有返回值抽象方法接口
     */
    interface MathOperation {
        int operation(int a, int b);
    }

    /**
     * 无返回值抽象方法接口
     */
    interface GreetingService {
        void sayMessage(String message);
    }

    /**
     * 有返回值抽象方法接口（参数final类型）
     */
    interface FinalTest {
        int add(final int a, final int b);
    }

/** **************************************************************线程************************************************* */

    /**
     * 自定义Runnable线程
     */
    class MyThread implements Runnable {
        @Override
        public void run() {
            System.out.println("thread 1");
        }
    }

    /**
     * jdk8中接口可以有非抽象方法，前提该方法必须为static或者default
     */
    interface Process {
        static void process(Runnable runnable) {
            new Thread(runnable).start();
        }
    }

}
