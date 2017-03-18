package com.spring.security.restful.springboot.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	 @Autowired
	    protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
	        auth.inMemoryAuthentication()
	                .withUser("abcde").password("fghij").roles("USER");
	    }
	   
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests().anyRequest().fullyAuthenticated();
	        http.httpBasic();
	        http.csrf().disable();
	    }
	    
	 
	/* @Bean
	    public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint(){
	        return new CustomBasicAuthenticationEntryPoint();
	    }
	    
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	  
	      http.csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/user/**").hasRole("ADMIN")
	        .and().httpBasic().realmName(REALM).authenticationEntryPoint(getBasicAuthEntryPoint())
	        .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//We don't need sessions to be created.
	    }*/
	
	
}
