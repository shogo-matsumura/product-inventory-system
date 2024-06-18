package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf().and() // CSRF保護を有効にする（元々は無効化されていた）
        .authorizeHttpRequests(authorizeRequests ->
            authorizeRequests
                .requestMatchers("/login", "/register").permitAll()
                .requestMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
        )
        .formLogin(formLogin ->
            formLogin
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
        )
        .logout(logout ->
            logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/logout-success") // ログアウト成功時にリダイレクトするURLを /login から /logout-success に変更
                .permitAll()
        );

    return http.build();
}
}
