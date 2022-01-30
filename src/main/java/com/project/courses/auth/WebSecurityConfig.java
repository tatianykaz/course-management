package com.project.courses.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationEntryPoint authEntryPoint;

	
	@Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
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
 
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	PasswordEncoder encoder = passwordEncoder();
//
//        auth
//                .inMemoryAuthentication()
//                    .withUser("john")
//                        .password(encoder.encode("abc123"))
//                        .roles("ADMIN");
//    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    		http
    			.csrf().disable()
    			.authorizeRequests()
    			.antMatchers("/users/all").hasAnyAuthority("ADMIN")
    			.antMatchers("/courses/new").hasAnyAuthority("ADMIN")
    			.anyRequest().authenticated()
    			.and().httpBasic()
    			.authenticationEntryPoint(authEntryPoint);
        
        http.authenticationProvider(authenticationProvider());
    }

}
