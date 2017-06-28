package com.ld.practice.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Lambda 表达式
 */
public class LambdaExpression {


    /* ==================================================
     * 一个简单的 Lambda 例子
     * Lambda 能省略匿名对象的创建
     *
     * 下面的例子用 Lambda 表达式替代创建匿名 Comparator 对象
     * ==================================================
     */
    private List<String> names = Arrays.asList("A", "B", "C", "D");

    /**
     * 旧的方式
     */
    public void oldMethod() {
        Collections.sort(names, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
    }

    /**
     * Lambda 表达式写法
     */
    public void lambdaMethod() {
        Collections.sort(names, (a, b) -> b.compareTo(a));
    }



    /* ==================================================
     * Functional Interfaces(函数化接口)
     * @FunctionalInterface 接口只能定义一个抽象方法，用于满足 Lambda 表达式
     *
     * 一下例子创建了一个 @FunctionalInterface 注解的接口，然后用 Lambda 表达式实现。
     * ==================================================
     */
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    @SuppressWarnings("unused")
    public void functionalInterfaceMethod() {
        // 旧的实现方式
        Converter<String, Integer> oldConverter = new LambdaExpression.Converter<String, Integer>() {

            @Override
            public Integer convert(String from) {
                return Integer.valueOf(from);
            }
        };

        // Lambda 实现方式
        Converter<String, Integer> lambdaConverter = (from) -> Integer.valueOf(from);
    }



    /* ==================================================
     * Method and Constructor References(方法和构造器的引用)
     * Java 8 能够传递一个方法或者构造器引用通过 ::关键字.
     *
     * methodReferences() 是用 :: 关键字引用静态和自定义方法的例子
     * constructorReferences() 是引用构造器的例子
     * ==================================================
     */
    class Something {
        public Integer doSomething(String s) {
            return Integer.valueOf(s);
        }
    }

    @SuppressWarnings("unused")
    public void methodReferences() {
        // 引用静态方法
        Converter<String, Integer> converter = Integer::valueOf;

        // 引用自定义方法
        Something something = new Something();
        Converter<String, Integer> converter2 = something::doSomething;
    }

    // 引用构造器 ------- begin
    class Person {
        String firstName;
        String lastName;

        public Person() {}
        public Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    public void constructorReferences() {
        PersonFactory<Person> personFactory = Person::new;    // 通过参数匹配构造器
        personFactory.create("firstName", "lastName");
    }
    // -------- end



    /* ==================================================
     * Lambda Scopes(Lambda 的作用域)
     *
     * scopes1() Accessing local variables(访问本地变量)
     * Scopes2   Accessing fields and static variables(访问属性和静态变量)
     * 最后                Accessing Default Interface Methods(访问默认的接口方法) 不能访问接口的 default 方法
     * ==================================================
     */
    @SuppressWarnings("unused")
    public void scopes1() {
        final int num = 0;
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from + num);    // lambda 表达式能够从局部读取一个的final修饰的局部变量

        int num2 = 1;
        Converter<String, Integer> converter2 = (from) -> Integer.valueOf(from + num2);    // 和匿名对象不同的是，在匿名对象中变量num并不需要用 final修饰. 也能正常调用

//        然而num必须隐式使用final修饰才能编译. 下面代码就不会编译
//        int num3 = 1;
//        Converter<String, Integer> converter3 = (from) -> Integer.valueOf(from + num3);
//        num3 = 2;
    }

    static class Scopes2 {
        static int outerStaticNum;
        int outerNum;

        @SuppressWarnings("unused")
        public void scopes() {
            Converter<String, Integer> converter1 = (from) -> {    // 读取全局变量
                outerNum = 2;
                return Integer.valueOf(from + outerNum);
            };

            Converter<String, Integer> converter2 = (from) -> {    // 读取全局静态变量
                outerStaticNum = 2;
                return Integer.valueOf(from + outerStaticNum);
            };
        }
    }

}
