package com.vcgdev.store.peperoni.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**").authorizeRequests().
                antMatchers("/oauth/token**", "/oauth/check_token/**",
                        "/v2/api-docs**", "/info", "/encoder").permitAll()
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                .failureForwardUrl("/login?error")
                .permitAll().and().csrf().disable()
                .httpBasic().disable();
    }
}
