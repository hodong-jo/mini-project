package com.jhd.hops.minimw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// String MVC 에 대한 설정파일. 웹에대한 설정파일
@Configuration
public class WebConfig implements WebMvcConfigurer{

	// CORS에 대한 설정
	// 프론트 엔드는 3000번 포트 (React.js), 백 엔드는 8080번 포트
	// http://localhost:3000 --> 8080 api를 호출할 수 있도록 설정.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("http://localhost:3000")
				.allowedMethods("GET", "POST", "PATCH", "PUT", "OPTIONS", "DELETE")
				.allowCredentials(true);
	}
}
