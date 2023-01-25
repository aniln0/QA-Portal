package com.qaportal.questions.jwtconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable();
//		httpSecurity.authorizeRequests()
//        .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
//        .anyRequest().authenticated();
	}

}
