package com.youmeek.java.pojo;

import java.util.Date;

public class Student {

	private int id;
	private String name;
	private int age;
	private Date createDatetime;

	public Student(int id, String name, int age, Date createDatetime) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.createDatetime = createDatetime;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", age=" + age +
				", createDatetime=" + createDatetime +
				'}';
	}
}
