package com.example.db;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DataSourceSpringExample {

	public static void main(String[] args) throws SQLException {
		// BeanFactory, DI Container
		// LifeCycle
		// 1.객체 생성
		// 2. 객체 초기화
		// 3. Dependency Injection
		// 4. 객체 사용
		// 5. 객체 소멸
		GenericXmlApplicationContext ctx =		
				new GenericXmlApplicationContext("classpath:/spring/beans.xml");
//빈 설정 해놓으면 spring이 new한다. Framework 활용
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("after BeanFactory create....");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
//		CityDao dao = ctx.getBean(CityDao.class);	//Bean 사용
		CountryDao dao = ctx.getBean(CountryDao.class);
		System.out.println(dao.selectAll());
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("before BeanFactory ctx.close");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		ctx.close();
	}

}
