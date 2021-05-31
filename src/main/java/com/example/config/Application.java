package com.example.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

// TODO: Auto-generated Javadoc
/**
 * The Class Application.
 *
 * @author shivam.rai
 */
@SpringBootApplication
@EnableEurekaClient

@ComponentScan(basePackages = {
		"com.ecommerce.controller",
		"com.ecommerce.dto",
		"com.ecommerce.service",
		"com.ecommerce.service.impl"
})

@EnableFeignClients
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
 