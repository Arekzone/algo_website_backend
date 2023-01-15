package com.shop.api.security;

import com.shop.service.JWTService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.stereotype.Component;

@Configuration
public class WebsecurityConfig {

    private JWTRequestFilter jwtRequestFilter;

    public WebsecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable().cors();
        httpSecurity.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        httpSecurity.authorizeHttpRequests()
                .requestMatchers("/product","/auth/register","/auth/login","/users","/admin/form","/admin/users","/zadania/all","/kompilator","/zadania/komentarze/902","/zadania/komentarze/902/7").permitAll()
                .anyRequest().authenticated();
        return httpSecurity.build();
    }
}
