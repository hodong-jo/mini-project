package com.jhd.hops.minimw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import lombok.RequiredArgsConstructor;

// String Security 설정.
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	private final AuthenticationManagerConfig authenticationManagerConfig;
	private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
	
	// JWT토큰을 인증을 한다. 그래서 HttpSession을 사용하지 않는다.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 무상태로 사용
				.and()
				.formLogin().disable()  // 직접 id, password를 입력 받아서 JWT 토큰을 리턴하는 API를 직접 만든다.
				.csrf().disable()  // CSRF 공격을 막기위한 방법인데 불편하게 많아서 끔
				.cors()
				.and()
				.apply(authenticationManagerConfig)
				.and()
				.httpBasic().disable()
				.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()  // Preflight 요청은 허용한다. (사전인증?)
				.mvcMatchers("/signup", "/login", "/users/refresh").permitAll()  // 회원가입, 로그인, 리프래쉬토큰 을 위한 api는 권한이있든없든 호출하도록
				.mvcMatchers(HttpMethod.GET, "/**").hasAnyRole("USER", "MANAGER", "ADMIN")  // GET, POST방식은 권한 있어야 접근가능하도록
				.mvcMatchers(HttpMethod.POST, "/**").hasAnyRole("USER", "MANAGER", "ADMIN")
				.anyRequest().hasAnyRole()
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
				.build()
				;
	}
	
	// 암호를 암호화하거나, 사용자가 입력한 암호가 기존 암호랑 일치하는지 검사할 때 이 Bean을 사용
	@Bean
	public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
}
