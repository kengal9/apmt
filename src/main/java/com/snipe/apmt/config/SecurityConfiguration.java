package com.snipe.apmt.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		        http.cors().and().csrf().disable().authorizeRequests()
				//.antMatchers("/api/auth/**").permitAll()
		        
		        .antMatchers("/api/admin/**").hasRole("ADMIN")
		        .antMatchers("/api/verification_manager/**").hasRole("VERIFICATION_MANAGER")
		        .antMatchers("/api/salesmanager/**").hasRole("SALES_MANAGER")
		        //.antMatchers("/api/student/**").hasRole("STUDENT")
		        .antMatchers("/api/student/**").access("hasRole('STUDENT')")
		        .antMatchers("/api/purchaser/**").hasRole("PURCHASER")
		        
				.antMatchers("/v1/**", "/v2/**", "/swagger-resources/configuration/ui", "/swagger-resources",
						"/swagger-ui.html", "/webjars/**")
				.permitAll()
				// .antMatchers("/v1/deleteCountry").access("hasRole('ADMIN')")

				.anyRequest().authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("http://localhost:4200");
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
