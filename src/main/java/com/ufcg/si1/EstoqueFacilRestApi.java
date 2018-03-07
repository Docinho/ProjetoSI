package com.ufcg.si1;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.ufcg.si1.controller.TokenFilter;

@SpringBootApplication(scanBasePackages = { "com.ufcg.si1" }) // same as @Configuration @EnableAutoConfiguration
																// @ComponentScan combined
public class EstoqueFacilRestApi {
	
	@Bean
	public FilterRegistrationBean getFilter() {
		FilterRegistrationBean frb = new FilterRegistrationBean();
		frb.setFilter(new TokenFilter());
		frb.addUrlPatterns("/api/*");
		
		return frb;
	}

	public static void main(String[] args) {
		SpringApplication.run(EstoqueFacilRestApi.class, args);
	}
}
