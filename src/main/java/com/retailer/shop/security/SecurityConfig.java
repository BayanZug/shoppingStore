package com.retailer.shop.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 
 * Standard Spring security configuration class
 *
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${spring.admin.username}")
    private String adminUsername;

    @Value("${spring.admin.password}")
    private String adminPassword;
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());

		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		
		 auth.inMemoryAuthentication()
         .withUser(adminUsername).password(passwordEncoder().encode(adminPassword)).roles("ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/login", "/oauth/**","/shoppingCart/**","/registration/**","/assets/**","/productImage/**")
		.permitAll()
		.antMatchers("/admin/**")
		.hasAnyAuthority("ROLE_ADMIN")
		.anyRequest().authenticated()
		.and().formLogin().permitAll().loginPage("/login").usernameParameter("email").passwordParameter("pass")
		.defaultSuccessUrl("/").and()
		.logout().logoutSuccessUrl("/login").permitAll()
		.and().exceptionHandling().accessDeniedPage("/403");
	}



}
