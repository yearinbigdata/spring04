package com.example.spring;

import org.springframework.context.support.GenericXmlApplicationContext;

public class DeptSpringExample {

	public static void main(String[] args) {

		/*
		 * spring의 bean 설정파일을 이용하면, 객체 생성 시 new, 초기화 과정이 생략된다.
		 */
		
		//GenericXmlApplicationContext = bean 공장
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:/spring/beans.xml");
		
		Dept dept = ctx.getBean(Dept.class);
		
		System.out.println(dept);
		System.out.println(dept.getDeptno() + ", " + dept.getDname() + ", " + dept.getLoc());

		
		ctx.close();
		
	}

}
