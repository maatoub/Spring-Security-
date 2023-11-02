package com.example.springdemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
     * @Bean
     * public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
     * return new JdbcUserDetailsManager(dataSource);
     * }
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> {
                    authorize
                            // Configure public resources
                            .requestMatchers("/").permitAll()
                            .requestMatchers("/accessory/**").hasRole("ADMIN")
                            .requestMatchers("/category/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .httpBasic(Customizer.withDefaults())
                .formLogin(login -> login.defaultSuccessUrl("/home"));
        return http.build();
    }
}
