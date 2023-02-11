package com.swathi.in_mem_auth.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	// To authenticate users in memory - hardcoded users and authorities
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Hard coded user authentication values
		auth.inMemoryAuthentication()
		.withUser("foo")
		.password("foo")
		.roles("USER")
		.and()
		.withUser("bar")
		.password("bar")
		.roles("ADMIN");
	}
	
	// Need to encode password as Spring security doesn't allow passwords to be set as plain text
	@Bean
	public PasswordEncoder getPasswordEncoding() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	// To authorize users of different roles with different permissions
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")		
		.antMatchers("/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/").permitAll()
		.and().formLogin();
	}
}

