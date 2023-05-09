package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("jj").password(this.passwordEncoder().encode("kk")).roles("ADMIN")
				.authorities("ACCESS_HOME");
		auth.inMemoryAuthentication().withUser("jjk").password(this.passwordEncoder().encode("kkk")).roles("ADMIN1");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/public/login").permitAll().antMatchers("/public/home")
				.hasAnyAuthority("ACCESS_HOME").antMatchers("/public/**").hasRole("ADMIN").antMatchers("/users/**")
				.hasRole("ADMIN1").anyRequest().authenticated().and().httpBasic();

	}

	// Spring Security configuration to encode the password.
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
