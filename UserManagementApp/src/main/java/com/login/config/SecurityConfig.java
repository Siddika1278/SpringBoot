package com.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
@EnableWebSecurity
public class SecurityConfig  {
	
	@Autowired
	public AuthenticationSuccessHandler customSucessHandler;
	
	@Bean
	UserDetailsService getuserDetailsService() {
		return new UserDetailsServiceImpl();
	}

    @Bean
    BCryptPasswordEncoder getpasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	 DaoAuthenticationProvider getDaoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getuserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getpasswordEncoder());
		
		return daoAuthenticationProvider;
	}
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(getDaoAuthenticationProvider());
	}
	@Bean
	 SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/admin/**")
		.hasRole("ADMIN")
		.requestMatchers("/user/**")
		.hasAnyRole("USER")
		.requestMatchers("/**")
		.permitAll()
		.and()
		.formLogin()
		.loginPage("/signin")
		.loginProcessingUrl("/login")
		.successHandler(customSucessHandler)
		.and()
		.sessionManagement()
		.invalidSessionUrl("/signin?invalid-session=true");
		
		return httpSecurity.build();
	}

}