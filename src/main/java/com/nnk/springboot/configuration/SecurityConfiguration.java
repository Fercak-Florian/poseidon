package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.nnk.springboot.services.MyUserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	MyUserDetailsService userDetailsService;

	String[] staticResources = {
			"/login",
			"/css/**",
			"/oauth2/**",
			"/img/**"
	};

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/user/list", "/user/add", "/user/update").hasAuthority("ADMIN")
		.antMatchers(staticResources)
		.permitAll()
		.anyRequest().authenticated()
		
		.and()
		.formLogin()
		.loginPage("/login")
		.defaultSuccessUrl("/", true)
		.failureUrl("/login?error=true")
		.permitAll()
		
		.and()
		.logout()
		.invalidateHttpSession(true).clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/app-logout"))
        .logoutSuccessUrl("/login")
        .permitAll()
        
        .and()
        .oauth2Login()
        .loginPage("/login");
		
		http.authenticationProvider(authenticationProvider());
		return http.build();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
