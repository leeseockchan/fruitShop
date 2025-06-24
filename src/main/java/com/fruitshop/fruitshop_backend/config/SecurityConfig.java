package com.fruitshop.fruitshop_backend.config;

import jakarta.servlet.http.HttpServletResponse;
import com.fruitshop.fruitshop_backend.component.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, UserDetailsService userDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.userDetailsService = userDetailsService;
    }

    // 관리자용 시큐리티
    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        log.info("Admin Security config ...");

        http
                .securityMatcher("/admin/**")  // 관리 페이지만 적용
                .csrf(AbstractHttpConfigurer::disable)  // API 서버역할
                .formLogin(formLogin-> formLogin
                        .loginPage("/admin/login")
                        .loginProcessingUrl("/admin/login")
                        .defaultSuccessUrl("/admin/dashboard",true)
                        .permitAll()
                )
                .authorizeHttpRequests(authorize-> authorize
                        // 관리자 요청 관리
                        .requestMatchers("/admin/login").permitAll()
                        .requestMatchers("/error").permitAll()
                        .requestMatchers("/admin/user/*").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/admin/dashboard").hasAnyRole("ADMIN", "MANAGER")

                        .anyRequest().authenticated()
                )
                .logout(logout-> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/admin")
                        .permitAll()
                );
        return http.build();
    }

    // API 서버용 시큐리티
    @Bean
    @Order(2)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception{
        log.info("API Security config ...");

        http
                .securityMatcher("/api/**")
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 또는 Stateless 환경
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/public/**").permitAll() // 공개 API
                        .requestMatchers("/api/v1/auth/login", "/api/v1/auth/signup").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
