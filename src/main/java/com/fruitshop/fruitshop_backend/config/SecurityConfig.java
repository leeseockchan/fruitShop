package com.fruitshop.fruitshop_backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("security config ...");

        http
                .formLogin(formLogin -> formLogin
                        .loginPage("/admin/login")
                        .defaultSuccessUrl("/admin/dashboard", true)
                        .permitAll()
                )
                .authorizeHttpRequests(authorize -> authorize
                        // 리액트 서버 요청 관리
                        .requestMatchers("/api/**").permitAll()
                        // 관리자 요청 관리
                        .requestMatchers("/admin/login").permitAll()
                        .requestMatchers("/admin/user/{id}").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER")
                        // 그외 인증 요구
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/admin")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
