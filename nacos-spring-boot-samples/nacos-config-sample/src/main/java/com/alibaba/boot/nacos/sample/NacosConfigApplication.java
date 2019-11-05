package com.alibaba.boot.nacos.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.alibaba.nacos.api.config.annotation.NacosValue;

@SpringBootApplication
public class NacosConfigApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NacosConfigApplication.class, args);
	}
	
	@Bean
	public Apple apple() {
		return new Apple();
	}
	
	@Bean
	@Order(Ordered.LOWEST_PRECEDENCE - 1)
	public CommandLineRunner secondCommandLineRunner() {
		return new SecondCommandLineRunner();
	}
	
	@Bean
	public Foo foo() {
		return new Foo();
	}
	
	@Configuration
	@ConditionalOnProperty(prefix = "people", name = "enable", havingValue = "true")
	protected static class People {

		@Bean
		public Object object() {
			System.err.println("[liaochuntao] : " + this.getClass().getCanonicalName());
			return new Object();
		}
	}
	
	public static class SecondCommandLineRunner implements CommandLineRunner {

		@NacosValue("${dept:unknown}")
		private String dept;

		@NacosValue("${group:unknown}")
		private String group;

		@Autowired
		private Foo foo;

		@Override
		public void run(String... args) throws Exception {
			System.out.println("Second runner. dept: " + dept + ", group: " + group);
			System.out.println("Second runner. foo: " + foo);
		}
	}
}
