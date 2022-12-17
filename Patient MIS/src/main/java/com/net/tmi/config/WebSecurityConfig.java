 package com.net.tmi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.net.tmi.entity.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
	    .antMatchers("/admin/**").authenticated()
	    .antMatchers("/app").authenticated()
	    .anyRequest().permitAll()
	    .and()
		    .formLogin()
		         .loginPage("/login")
		         .successHandler(successHandler)
	             .permitAll()
	     	    .and()
	    	    .logout()
	    	    .invalidateHttpSession(true)
	    	    .clearAuthentication(true)
	    	    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	    	    .logoutSuccessUrl("/?logout")
	    	    .permitAll();
		
//		http.authorizeRequests()
//	    .antMatchers("/").permitAll()
//	    .anyRequest().authenticated()
//	    .and()
//	    .formLogin()
//	    .loginPage("/login")
//	          .permitAll()
//	    .and()
//	    .logout()
//	    .invalidateHttpSession(true)
//	    .clearAuthentication(true)
//	    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//	    .logoutSuccessUrl("/?logout")
//	    .permitAll();
		
		   // .antMatchers("/users/**").hasRole("USER")
		   // .antMatchers("/admin/**").permitAll()
		    //.anyRequest().permitAll()
		    //.and(
		
		
		
//If worse Role back to below code		
		
//		http.authorizeRequests()
//	    .antMatchers("/admin/**").authenticated()
//	    .antMatchers("/app").authenticated()
//	    .anyRequest().permitAll()
//	    .and()
//		    .formLogin()
//		         .usernameParameter("email")
//		         .defaultSuccessUrl("/")
//		          .permitAll()
//		    .and()
//		    .logout()
//		    .logoutSuccessUrl("/")
//		    .permitAll();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/images/**","/js/**","/webjars/**");
	}
	@Autowired private LoginSuccessHandler successHandler;
	
}
