package com.youmeek.java.main;

import com.youmeek.java.pojo.Student;
import com.youmeek.java.pojo.StudentDTO;
import com.youmeek.java.utils.GenerateStudentUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StreamTest {


	//=====================================Map 类型处理 start=====================================

	/**
	 * 找出名字中包含 e 的所有对象
	 *
	 * @throws ParseException
	 */
	@Test
	public void test231() throws ParseException {
		Map<String, Student> studentMap = GenerateStudentUtils.map();

		// 写法一
		Map<String, Student> resultMap = studentMap.entrySet().stream()
				.filter(x -> (x.getValue().getName()).contains("e"))
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

		// 写法二
		Map<String, Student> resultMap2 = studentMap.entrySet().stream()
				.filter(x -> (x.getValue().getName()).contains("e"))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
		resultMap2.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 找出名字中包含 e 的所有名字，用逗号隔开
	 *
	 * @throws ParseException
	 */
	@Test
	public void test2131() throws ParseException {
		Map<String, Student> studentMap = GenerateStudentUtils.map();

		String result = studentMap.entrySet().stream()
				.filter(x -> (x.getValue().getName()).contains("e"))
				.map(x -> x.getValue().getName())
				.collect(Collectors.joining(","));

		System.out.println("--------------------------------" + result);
	}


	/**
	 * 找出名字中包含 e，并且年龄大于 20 的所有对象
	 *
	 * @throws ParseException
	 */
	@Test
	public void test23431() throws ParseException {
		Map<String, Student> studentMap = GenerateStudentUtils.map();

		Map<String, Student> resultMap = studentMap.entrySet().stream()
				.filter(x -> {
					if (!x.getValue().getName().contains("e") && x.getValue().getAge() > 20) {
						return true;
					}
					return false;
				})
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}


	/**
	 * 找出名字中包含 e，并且年龄大于 20 的所有名字，用逗号隔开
	 *
	 * @throws ParseException
	 */
	@Test
	public void test23311() throws ParseException {
		Map<String, Student> studentMap = GenerateStudentUtils.map();

		String result = studentMap.entrySet().stream()
				.filter(x -> {
					if (!x.getValue().getName().contains("e") && x.getValue().getAge() > 20) {
						return true;
					}
					return false;
				})
				.map(map -> map.getValue().getName())
				.collect(Collectors.joining(","));

		System.out.println("--------------------------------" + result);
	}


	/**
	 * 多个条件筛选（利用 Predicate）
	 *
	 * @throws ParseException
	 */
	@Test
	public void test2334431() throws ParseException {
		Map<String, Student> studentMap = GenerateStudentUtils.map();

		Map<String, Student> resultMap = filterByValue(studentMap, x -> x.getName().contains("e"));
		Map<String, Student> resultMap2 = filterByValue(studentMap, x -> (x.getName().contains("e") && x.getAge() > 20));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
		System.out.println("--------------------------------");
		resultMap2.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
	}

	/**
	 * 多个条件筛选（利用 Predicate）（private）
	 *
	 * @throws ParseException
	 */
	private <K, V> Map<K, V> filterByValue(Map<K, V> map, Predicate<V> predicate) {
		return map.entrySet()
				.stream()
				.filter(x -> predicate.test(x.getValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	/**
	 * 找出 key 包含 A 的 Map
	 *
	 * @throws ParseException
	 */
	@Test
	public void test2331() throws ParseException {
		Map<String, Student> studentMap = GenerateStudentUtils.map();

		// 写法一
		Map<String, Student> resultMap = studentMap.entrySet().stream()
				.filter(x -> x.getKey().contains("A"))
				.collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

		// 写法二
		Map<String, Student> resultMap2 = studentMap.entrySet().stream()
				.filter(x -> x.getKey().contains("A"))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

		resultMap.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));
		System.out.println("--------------------------------");
		resultMap2.forEach((k, v) -> System.out.println("Key : " + k + " Value : " + v));

	}


	//=====================================Map 类型处理  end=====================================


	//=====================================List 类型处理 start=====================================

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
	 * 把 list 中的数值都乘于 2
	 */
	@Test
	public void test10() {
		List<Integer> num = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> resultList = num.stream().map(n -> n * 2).collect(Collectors.toList());
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
	 * 找出所有学生名字（List of objects -> List of String）
	 */
	@Test
	public void test2() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		List<String> userNameList = studentList.stream().map(x -> x.getName()).collect(Collectors.toList());

		userNameList.forEach(System.out::println);
	}


	/**
	 * 找出年龄大于 20 的用户（List 对象）
	 */
	@Test
	public void test212() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		List<Student> resultList =
				studentList.stream()
						.filter(user -> user.getAge() > 20)
						.collect(Collectors.toList());

		resultList.forEach(e -> System.out.println("Id:" + e.getId() + ", Name: " + e.getName() + ", Age:" + e.getAge() + ", CreateDatetime:" + e.getCreateDatetime()));
	}


	/**
	 * 找出年龄大于 20，并且用户名为 DuskLife 的用户（单个对象）
	 */
	@Test
	public void test2112() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		// 返回的是单个对象，不是 List
		Student student1 = studentList.stream()
				.filter((p) -> "DuskLife".equals(p.getName()) && 20 < p.getAge())
				.findAny()
				.orElse(null);

		System.out.println("student1 :" + student1);

		// 另外一种写法
		Student student2 = studentList.stream()
				.filter(p -> {
					if ("SayShy".equals(p.getName()) && 20 < p.getAge()) {
						return true;
					}
					return false;
				}).findAny()
				.orElse(null);

		System.out.println("student2 :" + student2);
	}

	/**
	 * 找出年龄大于 20 的用户名（List 对象）
	 */
	@Test
	public void test21221() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		List<String> userNameList =
				studentList.stream()
						.filter(user -> user.getAge() > 20)
						.map(Student::getName)
						.collect(Collectors.toList());

		userNameList.forEach(System.out::println);
	}

	/**
	 * 找出年龄大于 20 的用户名，并且用逗号隔开（List 对象）
	 */
	@Test
	public void test211221() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		String result = studentList.stream()
				.filter(user -> user.getAge() > 20)
				.map(Student::getName)
				.collect(Collectors.joining(","));

		System.out.println("--------------------------------" + result);
	}

	/**
	 * 找出年龄大于 20 的用户名（单个对象）
	 */
	@Test
	public void test2121() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		String name = studentList.stream()
				.filter(x -> "DuskLife".equals(x.getName()))
				.map(Student::getName)                        //convert stream to String
				.findAny()
				.orElse("");

		System.out.println("name : " + name);
	}


	/**
	 * 找出年龄大于 20 的用户名，并且根据用户创建时间排序
	 */
	@Test
	public void test22() throws ParseException {
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
	 * 把 PO 转换成 DTO（List of objects -> List of other objects）
	 */
	@Test
	public void test222() throws ParseException {
		List<Student> studentList = GenerateStudentUtils.list();

		List<StudentDTO> resultList = studentList.stream().map(temp -> {
			StudentDTO obj = new StudentDTO();
			obj.setMyId(temp.getId());
			obj.setMyName(temp.getName());
			obj.setMyAge(temp.getAge());
			obj.setMyCreateDatetime(temp.getCreateDatetime());
			return obj;
		}).collect(Collectors.toList());

		resultList.forEach(e -> System.out.println("Id:" + e.getMyId() + ", Name: " + e.getMyName() + ", Age:" + e.getMyAge() + ", CreateDatetime:" + e.getMyCreateDatetime()));

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
	//=====================================List 类型处理  end=====================================


}

