package com.restful.rest.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder

import org.springframework.http.HttpMethod

@EnableWebSecurity
open class SecurityConfig: WebSecurityConfigurerAdapter() {

	override fun configure(auth: AuthenticationManagerBuilder){
		auth.inMemoryAuthentication()
			.withUser("user").password("{noop}user").roles("USER")
			.and()
			.withUser("admin").password("{noop}admin").roles("ADMIN")
  	}

	override fun configure(http: HttpSecurity) {
		http.csrf().disable()
		http
			.httpBasic().and()
            .authorizeRequests()
                .antMatchers("/tasks", "/tag/all").permitAll()
				.antMatchers(HttpMethod.POST, "/task/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/task/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/tag/**").hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/tag/**").hasRole("ADMIN")
                .anyRequest().authenticated();
        http
            .formLogin()
                .permitAll()
        .and()
            .logout()
                .permitAll();
	}
}