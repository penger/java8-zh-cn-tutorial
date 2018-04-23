package com.youmeek.java.main;

import com.youmeek.java.pojo.Student;
import com.youmeek.java.utils.GenerateStudentUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Optional;
import java.util.stream.Collectors;

public class StreamTest {

	/**
	 * 把所有字符串改为大写后输出
	 */
	@Test
	public void test1() {
		List<String> wordList = Arrays.asList("abc", "bcd", "cde");
		List<String> resultList = wordList.stream().map(String::toUpperCase).collect(Collectors.toList());
		resultList.forEach(System.out::println);
	}


	/**
	 * 根据 创建时间 正序
	 */
	@Test
	public void test11() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		List<Student> resultList = studentList.stream().sorted(Comparator.comparing(Student::getCreateDatetime)).collect(Collectors.toList());
		resultList.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e.getCreateDatetime()));
	}

	/**
	 * 根据 创建时间 倒序
	 */
	@Test
	public void test12() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		List<Student> resultList = studentList.stream().sorted(Comparator.comparing(Student::getCreateDatetime).reversed()).collect(Collectors.toList());
		resultList.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e.getCreateDatetime()));
	}


	/**
	 * 找出年龄大于 20 的用户名，根据用户创建时间排序
	 */
	@Test
	public void test2() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		List<String> userNameList =
				studentList.stream()
						.filter(user -> user.getAge() > 20)
						.sorted(Comparator.comparing(Student::getCreateDatetime))
						.map(Student::getName)
						.collect(Collectors.toList());

		userNameList.forEach(System.out::println);
	}

	/**
	 * 计算所有年龄的总值
	 *
	 * @throws ParseException
	 */
	@Test
	public void test3() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		Optional<Integer> totalAge =
				studentList.stream()
						.map(Student::getAge)
						.reduce((n, m) -> n + m);

		System.out.println("--------------------------------计算所有年龄的总值=" + totalAge.get());
	}

	/**
	 * 年龄最高的人
	 *
	 * @throws ParseException
	 */
	@Test
	public void test4() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		Optional<Student> expensive = studentList.stream().max(Comparator.comparing(Student::getAge));
		System.out.println("--------------------------------年龄最高的人=" + expensive.get().getName() + " , 年龄=" + expensive.get().getAge());
	}

	/**
	 * 年龄最低的人
	 *
	 * @throws ParseException
	 */
	@Test
	public void test5() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		Optional<Student> cheapest = studentList.stream().min(Comparator.comparing(Student::getAge));
		System.out.println("--------------------------------年龄最低的人=" + cheapest.get().getName() + " , 年龄=" + cheapest.get().getAge());
	}

	/**
	 * Collectors 计算
	 *
	 * @throws ParseException
	 */
	@Test
	public void test6() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		LongSummaryStatistics longSummaryStatistics = studentList.stream().collect(Collectors.summarizingLong(Student::getAge));
		long sum = longSummaryStatistics.getSum();// 年龄汇总
		double average = longSummaryStatistics.getAverage();// 求平均数
		long totalCount = longSummaryStatistics.getCount();// 计算总数
		long max = longSummaryStatistics.getMax();// 最大值
		long min = longSummaryStatistics.getMin();          // 最小值
		String result = String.format("年龄汇总=%s, 求平均数=%s, 计算总数=%s, 最大值=%s, 最小值=%s", sum, average, totalCount, max, min);
		System.out.println("--------------------------------最终结果：" + result);
	}


}

