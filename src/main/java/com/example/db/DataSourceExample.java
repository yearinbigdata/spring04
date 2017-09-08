package com.example.db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.example.spring.Dept;

public class DataSourceExample {

	public static void main(String[] args) throws SQLException {
		
//		No FrameWork.전통적인 개발 방식. 이것을 bean설정으로 Framework를 사용해서 할 수 있다.
		
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/world");
		ds.setUsername("jspbook");
		ds.setPassword("1234");
		
		Connection conn = ds.getConnection();
		
		Dept dept = new Dept();
		
		conn.close();

	}

}
