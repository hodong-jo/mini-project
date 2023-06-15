package com.jhd.hops.minimw.security.jwt.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.jhd.hops.minimw.security.jwt.token.JwtAuthenticationToken;
import com.jhd.hops.minimw.security.jwt.util.JwtTokenizer;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider{
	
	private final JwtTokenizer jwtTokenizer;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 토큰을 검증한다. 기간이 만료되었는지, 토큰 문자열이 문제가 있는지 등 Exception이 발생한다.
		JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
		Claims claims = jwtTokenizer.parseAccess
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}

}
