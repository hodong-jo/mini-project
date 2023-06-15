package com.jhd.hops.minimw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jhd.hops.minimw.security.jwt.filter.JwtAuthenticationFilter;
import com.jhd.hops.minimw.security.jwt.provider.JwtAuthenticationProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AuthenticationManagerConfig extends AbstractHttpConfigurer<AuthenticationManagerConfig, HttpSecurity>{
	
	private final JwtAuthenticationProvider jwtAuthenticationProvider = null;
	
	@Override
	public void configure(HttpSecurity builder) throws Exception {
		AuthenticationManager authenticationManager = builder.getSharedObject(AuthenticationManager.class);
		
		builder.addFilterBefore(
				new JwtAuthenticationFilter(authenticationManager),
				UsernamePasswordAuthenticationFilter.class)
				.authenticationProvider(jwtAuthenticationProvider);
		
	}

}
