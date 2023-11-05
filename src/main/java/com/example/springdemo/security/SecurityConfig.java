package com.example.springdemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.springdemo.service.ServiceUserDetailsImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /*
     * 
     * @Bean
     * public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
     * return new JdbcUserDetailsManager(dataSource);
     * }
     * 
     */

    @Autowired
    private ServiceUserDetailsImp userDetailsImp;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> {
                    authorize
                            // Configure public resources
                            .requestMatchers("/login", "/css/**", "/register", "/registration").permitAll()
                            .requestMatchers("/accessory/**").hasRole("ADMIN")
                            .requestMatchers("/category/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })

                .formLogin(login -> login.loginPage("/login").defaultSuccessUrl("/home", true).permitAll());
        http.userDetailsService(userDetailsImp);
        http.exceptionHandling(handling -> handling.accessDeniedPage("/notAuthorized"));
        return http.build();
    }
}
