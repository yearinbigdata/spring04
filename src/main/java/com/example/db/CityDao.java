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

public class CityDao implements InitializingBean, DisposableBean{
//생성 시 호출. property를 다 셋팅한 이후에 2번을 호출하도록 정해져있음. 
	static Log log = LogFactory.getLog(CityDao.class);
	public CityDao(){
		log.info("###############");
		log.info("1. CityDao()...");
		log.info("###############");
	}
	//의존주입. 데이터 소스가 있어야 넣을 수 있음. DI 컨테이너가 넣어줌
	BasicDataSource ds;
	public void setDataSource(BasicDataSource ds){
		this.ds = ds;
		log.info("#####################");
		log.info("2. setDataSource()...");
		log.info("#####################");
		
	}
	
	// bean 초기화 방법 3가지
	
	@PostConstruct
	public void postConstruct(){
		log.info("#####################");
		log.info("3. @PostConstruct...");
		log.info("#####################");
	}
	
	@Override//initializingBean 구현
	public void afterPropertiesSet() throws Exception {
		log.info(ds.getDriverClassName());
		log.info(ds.getUrl());
		log.info(ds.getUsername());
		log.info(ds.getPassword());
		log.info("###########################################");
		log.info("4. InitializingBean.afterPropertiesSet()...");
		log.info("#############3#############################");
	}
	
	public void init() {
		log.info("#################");
		log.info("5. 커스텀 init()...");
		log.info("#################");
	}
	
	//사용하는 방법. 주입되어있기 때문에 db에 connect해서 가져올 수 있음
	public List<String> selectAll() throws SQLException{
		Connection conn = ds.getConnection();
		PreparedStatement pstmt = 
				conn.prepareStatement("select name from city where countryCode=?");
		pstmt.setString(1, "KOR");
		
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
	
	//destroy 방법 3가지
	
	@PreDestroy
	public void preDestrruct(){
		log.info("#################");
		log.info("6. @PreDestroy...");
		log.info("#################");
	}
	@Override
	public void destroy() throws Exception {
		log.info("##############################");
		log.info("7. DisposableBean.destroy()...");
		log.info("##############################");
	}
	
	public void close(){	//ctx.close 후에 호출됨
		log.info("###################");
		log.info("8. 커스텀 close()...");
		log.info("###################");
	}

}
