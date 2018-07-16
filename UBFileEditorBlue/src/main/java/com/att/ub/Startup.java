package com.att.ub;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.att.ub.*" })
public class Startup {

	private static final Logger LOGGER = Logger.getLogger(Startup.class);

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Startup.class, args);

		LOGGER.info("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			if (beanName.endsWith("Repository") || beanName.endsWith("Controller")) {
				LOGGER.info(beanName);
			}
		}
	}

}
