package com.demo.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 *
 * @author shivam.rai
 */
@SpringBootApplication
@EnableEurekaClient
/*@ComponentScan(basePackages = { 
		"com.demo.bank.controller",
		"com.demo.bank.service",
		"com.demo.bank.service.impl",
		"com.demo.bank.repository",
		"com.demo.bank.dto",
		"com.demo.bank.model",
		
 }) */

public class Application {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
