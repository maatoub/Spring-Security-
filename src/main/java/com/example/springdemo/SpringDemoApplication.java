package com.example.springdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import com.example.springdemo.service.AppService;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Bean
	PasswordEncoder pEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Bean
	CommandLineRunner commandLineRunner(JdbcUserDetailsManager detailsManager) {
		PasswordEncoder pEncoder = pEncoder();
		return args -> {
			detailsManager.createUser(
					User.withUsername("user1")
							.password(pEncoder.encode("1234"))
							.roles("USER").build());
			detailsManager.createUser(
					User.withUsername("nasser")
							.password(pEncoder.encode("1234"))
							.roles("USER", "admin").build());
		};

	}

	// @Bean
	CommandLineRunner commandLineRunnerUserDetails(AppService appService) {

		return args -> {
			/************* ROLE *************/
			appService.addRole("USER");
			appService.addRole("ADMIN");
			/************* USER *************/
			appService.addUser("admin1", "1234", "1234");
			appService.addUser("user1", "1234", "1234");
			/************* USER *************/
			appService.addRoleToUser("admin1", "ADMIN");
		};

	}

}