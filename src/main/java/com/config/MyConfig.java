package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class MyConfig {

	private final AuthenticationSuccessHandler customSuccessHandler;

	public MyConfig(AuthenticationSuccessHandler customSuccessHandler) {
		this.customSuccessHandler = customSuccessHandler;
	}

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/user/**", "/candidate/**").hasRole("NORMAL")
						.anyRequest().permitAll()
				)
				.formLogin(form -> form
						.loginPage("/signin")
						.loginProcessingUrl("/dologin")
						.successHandler(customSuccessHandler)
				)
				.logout(logout -> logout
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				)
				.sessionManagement(session -> session
						.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
				)
				.httpBasic(Customizer.withDefaults()); // Updated for Spring Security 6.1+

		return http.build();
	}
}
