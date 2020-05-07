package com.demo.springbootcrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@MapperScan(basePackages="com.demo.dao")
@ComponentScan(basePackages = {"com.demo.config", "com.demo.controller", "com.demo.service"})
public class SpringbootCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCrmApplication.class, args);
	}

}
