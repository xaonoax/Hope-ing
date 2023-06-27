package com.hopeing.mybatis;

import java.io.IOException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration  // 설정 관련 클래스
@RequiredArgsConstructor
@Slf4j
@MapperScan("com.hopeing.mappers")
public class MyBatisConfig {
	private final ApplicationContext applicationContext;
	
	// HikariCP 설정을 위한 Property 가져오기
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		return new HikariConfig();
	}
	
	// DataSource 설정
	@Bean
	public DataSource dataSource() {
		HikariDataSource hds = new HikariDataSource(hikariConfig());
		return hds;
		// return new HikariDataSource(hikariConfig());
	}
	
	// SQLSessionFactory 만들기
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws IOException {
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource());
		
		// SQL query를 작성할 xml 경로 설정
		sfb.setMapperLocations(applicationContext.getResources("classpath*:/mappers/*.xml"));
		
		// MyBatis를 작성할 파일의 경로 설정
		sfb.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));
		
		try {
			SqlSessionFactory factory = sfb.getObject();
			
			// DB의 컬럼명이 snake_case 형식인 경우 CamelCase로 변경하지 않음
			factory.getConfiguration().setMapUnderscoreToCamelCase(false);
			return factory;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
