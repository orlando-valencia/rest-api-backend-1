package com.kenzan;

import javax.ws.rs.HttpMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.headers().frameOptions().sameOrigin();
		http.csrf().ignoringAntMatchers("/console/**");
        http.authorizeRequests().antMatchers("/console/**").permitAll();
		http
		.httpBasic()
        .and()
        .authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/employees/").hasRole("USER")
        .antMatchers(HttpMethod.POST, "/api/employees/").hasRole("USER")
        .antMatchers(HttpMethod.PUT, "/api/employees/deactivate/**").hasRole("ADMIN")
        .antMatchers(HttpMethod.PUT, "/api/employees/activate/**").hasRole("ADMIN")
        .and()
        .csrf().disable()
        .formLogin().disable();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("user").password("{noop}password").roles("USER")
		.and()
		.withUser("admin").password("{noop}password").roles("USER", "ADMIN");
	}
}
