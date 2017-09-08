package com.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class CountryDao implements InitializingBean, DisposableBean{

	static Log log = LogFactory.getLog(CountryDao.class);
	public CountryDao(){
		log.info("##################");
		log.info("1. CountryDao()...");
		log.info("##################");
	}
	//주입
	BasicDataSource ds;
	public void setDataSource(BasicDataSource ds){
		this.ds=ds;
		log.info("#####################");
		log.info("2. setDataSource()...");
		log.info("#####################");
	}
	//초기화
	@PostConstruct
	public void postConstruct(){
		log.info("#####################");
		log.info("3. @PostConstruct()...");
		log.info("#####################");
	}
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info(ds.getDriverClassName());
		log.info(ds.getUrl());
		log.info(ds.getUsername());
		log.info(ds.getPassword());
		log.info("###########################################");
		log.info("4. InitializingBean.afterPropertiesSet()...");
		log.info("############################################");		
	}
	public void init(){
		log.info("############");
		log.info("5. init()...");
		log.info("############");
	}
	//사용
	public List<String> selectAll() throws SQLException{
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("select name from country");
		
		ResultSet rs = pstmt.executeQuery();
		
		List<String> list = new ArrayList<>();
		while(rs.next()){
			list.add(rs.getString("name"));
		}
		rs.close();
		pstmt.close();
		conn.close();		
		return list;
	}
	
	//소멸
	@PreDestroy
	public void preDestruct(){
		log.info("###################");
		log.info("6. @PreDestroy()...");
		log.info("###################");
	}
	@Override
	public void destroy() throws Exception {
		log.info("##############################");
		log.info("7. DisposableBean.destroy()...");
		log.info("##############################");
	}
	public void close(){
		log.info("#############");
		log.info("8. close()...");
		log.info("#############");
	}
	
}
