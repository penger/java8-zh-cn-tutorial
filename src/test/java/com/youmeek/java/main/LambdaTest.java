package com.youmeek.java.main;

import com.youmeek.java.pojo.Student;
import com.youmeek.java.utils.GenerateStudentUtils;
import javafx.util.Callback;
import org.junit.Test;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * lambda 表达式在简单使用上就是用编写函数的方式编写匿名内部类代码
 */
public class LambdaTest {


    /**
     * 循环 Map
     *
     * lambda 表达式:
     * (k, v) -> System.out.println("Key : " + k + " Value : " + v)
     *
     * 相当于
     * 实现 BiConsumer 接口的匿名内部类
     *
     * 箭头 `->` 后面的代码是 `BiConsumer` 接口的 `accept(String k, Student y)` 的实现
     */
    @Test
    public void testMapForEach() throws ParseException {
        Map<String, Student> resultMap = GenerateStudentUtils.map();

        resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

        resultMap.forEach((k, v) -> {
            System.out.println("Key : " + k + " Value : " + v);
            if ("E".equals(k)) {
                System.out.println("Hello E");
            }
        });
    }

    /**
     * 循环 List
     *
     * 写法一同上
     *
     * 写法二使用 Java 8 全新的双冒号(::)操作符可以将一个常规方法转化为 Lambda 表达式
     * 双冒号(::)的详细说明在最下面
     */
    @Test
    public void testList() throws ParseException {
        List<Student> studentList = GenerateStudentUtils.list();

        // 写法一
        studentList.forEach(item -> System.out.println(item));

        // 写法二
        studentList.forEach(System.out::println);

        studentList.forEach(item -> {
            if ("DuskLife".equals(item.getName())) {
                System.out.println(item);
            }
        });

    }

    /**
     * 自定义可写成lambda表达式的接口
     *
     * 从lambda表达式访问外部范围变量与匿名对象非常相似。
     *
     * 主要不同是 this
     * 对于匿名类，关键词 this 解读为匿名类，而对于 Lambda 表达式，关键词 this 解读为写就 Lambda 的外部类
     */
    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }
    int out = 2;
    @Test
    public void testScope(){
        final int num = 1;
        Converter<Integer, String> stringConverter =
                (from) -> String.valueOf(from + num + this.out);

        String s = stringConverter.convert(2);

        System.out.println(s);
    }

    /**
     * 根据 创建时间 正序
     */
    @Test
    public void test11() throws ParseException {
        List<Student> studentList = GenerateStudentUtils.list();

        // 写法一
        studentList.sort((Student o1, Student o2) -> o1.getCreateDatetime().compareTo(o2.getCreateDatetime()));

        // 写法二
        studentList.sort((o1, o2) -> o1.getCreateDatetime().compareTo(o2.getCreateDatetime()));

        // 写法三（推荐）
        studentList.sort(Comparator.comparing(Student::getCreateDatetime));

        studentList.forEach(e -> System.out
                .println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e
                        .getCreateDatetime()));
    }

    /**
     * 根据 创建时间 倒序
     */
    @Test
    public void test12() throws ParseException {
        List<Student> studentList = GenerateStudentUtils.list();


        // 写法一
        studentList.sort((Student o1, Student o2) -> o2.getCreateDatetime().compareTo(o1.getCreateDatetime()));

        // 写法二
        studentList.sort((o1, o2) -> o2.getCreateDatetime().compareTo(o1.getCreateDatetime()));

        // 写法三
        Comparator<Student> salaryComparator = (o1, o2) -> o1.getCreateDatetime().compareTo(o2.getCreateDatetime());
        studentList.sort(salaryComparator.reversed());

        // 写法四（推荐）
        studentList.sort(Comparator.comparing(Student::getCreateDatetime).reversed());

        studentList.forEach(e -> System.out
                .println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e
                        .getCreateDatetime()));
    }

    // =================================================================================================================
    // =================================================================================================================
    // =================================================================================================================


    /**
     * 在Java 8 你能够通过 `::` 关键字传递方法或构造函数的引用 (这个不像Lambda，它不需要在被传递的类添加注解)
     *
     * 下面方法是传递 `Integer` 类 的静态方法 `valueOf()` 引用的例子
     *
     * 例子里引用的声明可以用jdk自带的 `Callback`，也可以自己定义 `Converter`
     */
    @Test
    public void testDoubleColonStaticMethod(){
        Callback<String, Integer> callback = Integer::valueOf;
        Integer ret = callback.call("123");
        System.out.println(ret);   // 123

        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123
    }

    /**
     * 在Java 8 你能够通过 `::` 关键字传递方法或构造函数的引用 (这个不像Lambda，它不需要在被传递的类添加注解)
     *
     * 下面方法是传递构造函数引用的例子
     *
     * 我们通过Person :: new创建一个对Person构造函数的引用。
     * Java编译器通过匹配PersonFactory.create的签名自动选择正确的构造函数。
     */
    class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }
    }
    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }
    @Test
    public void testDoubleColonConstructors(){
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
        System.out.println(person);
    }
}

