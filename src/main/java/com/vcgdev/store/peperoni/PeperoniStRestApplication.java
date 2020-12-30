package com.vcgdev.store.peperoni;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PeperoniStRestApplication {

	private final DataSource dataSource;

	public PeperoniStRestApplication(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	public static void main(String[] args) {
		SpringApplication.run(PeperoniStRestApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
						.allowedMethods("POST", "GET", "PUT", "DELETE")
						.allowedOrigins("*");
			}
		};
	}

	@Bean
	public TokenStore tokenStore(){
		return new JdbcTokenStore(dataSource);
	}

	@Bean
	public UserDetailsService userDetailsService() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource);
		jdbcUserDetailsManager.
				setUsersByUsernameQuery("SELECT username, user_pwd, is_active as active FROM user_app WHERE username=?");
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
			"SELECT u.username, mp.privilege_code FROM user_app AS u " +
			"INNER JOIN user_profile AS p ON p.user_profile_id = u.user_profile_id "+
			"INNER JOIN profile_privilege AS urp ON urp.user_profile_id = u.user_profile_id "+
			"INNER JOIN module_privilege as mp ON mp.module_privilege_id = urp.module_privilege_id "+
						"WHERE u.username=?"
		);
		return jdbcUserDetailsManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
