/**
 * 
 */
package edu.binayak.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * @author HP
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//URL access
		
		http.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/home","/user","/h2-console/**")
		.permitAll()
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAnyAuthority("ADMIN")
		.antMatchers("/emp").hasAnyAuthority("EMPLOYEE")
		.antMatchers("/student").hasAuthority("STUDENT")
		.anyRequest().permitAll()
	
		// form login
		.and().formLogin().defaultSuccessUrl("/welcome",true)
		
		//logout
		
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		// exception
		
		.and()
		.exceptionHandling().accessDeniedPage("/denied");
	}
}