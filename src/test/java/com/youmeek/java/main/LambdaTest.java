package com.youmeek.java.main;

import com.youmeek.java.pojo.Student;
import com.youmeek.java.utils.GenerateStudentUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class LambdaTest {


	/**
	 * 循环 Map
	 */
	@Test
	public void test132() throws ParseException {
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
	 */
	@Test
	public void test1232() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();
		
		// 写法一
		studentList.forEach(item->System.out.println(item));
		
		// 写法二
		studentList.forEach(System.out::println);
		
		studentList.forEach(item -> {
			if ("DuskLife".equals(item.getName())) {
				System.out.println(item);
			}
		});

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

		studentList.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e.getCreateDatetime()));
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

		studentList.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e.getCreateDatetime()));
	}


}

