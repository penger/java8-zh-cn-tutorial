package com.youmeek.java.utils;

import com.youmeek.java.pojo.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateStudentUtils {

	public static List<Student> list() throws ParseException {

		String date1String = "2014-04-17 17:07:37";
		String date2String = "2018-04-17 17:07:37";
		String date3String = "2014-04-17 19:07:37";
		String date4String = "2016-02-22 19:07:37";
		String date5String = "2017-09-21 10:17:37";

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date date1 = simpleDateFormat.parse(date1String);
		Date date2 = simpleDateFormat.parse(date2String);
		Date date3 = simpleDateFormat.parse(date3String);
		Date date4 = simpleDateFormat.parse(date4String);
		Date date5 = simpleDateFormat.parse(date5String);


		List<Student> studentList = new ArrayList<Student>();
		studentList.add(new Student(1, "YouMeek", 12, date1));
		studentList.add(new Student(2, "Admin", 16, date2));
		studentList.add(new Student(3, "GitNavi", 10, date3));
		studentList.add(new Student(4, "SayShy", 22, date4));
		studentList.add(new Student(5, "DuskLife", 36, date5));
		
		return studentList;
	}
}
