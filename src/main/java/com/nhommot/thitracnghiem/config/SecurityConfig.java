//package com.nhommot.thitracnghiem.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//	@SuppressWarnings({ "removal", "removal", "removal", "removal", "removal", "removal", "removal" })
//	@Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            .authorizeHttpRequests()
//            	.requestMatchers("/css/**", "/js/**", "/images/**", "/public/**").permitAll()
//                .requestMatchers("/register-user", "/login-admin", "/register-admin").permitAll()
//                .anyRequest().authenticated()
//                .and()
//            .formLogin()
//                .loginPage("/login")
//                .defaultSuccessUrl("/homepage", true)
//                .loginProcessingUrl("/auth/login-user")
//                .permitAll()
//                .and()
//            .logout()
//                .logoutUrl("/auth/logout")
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutSuccessUrl("/login")
//                .permitAll()
//            .and()
//            .exceptionHandling()
//                .accessDeniedPage("/register-admin");
//
//        return http.build();
//    }
//}
