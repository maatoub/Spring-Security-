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
import com.example.springdemo.service.AppServiceImpl;
import com.example.springdemo.service.ServiceUserDetailsImp;

@SpringBootApplication
public class SpringDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
	}

	@Bean
	PasswordEncoder pEncoder() {
		return new BCryptPasswordEncoder();
	}

	

	/******************** inialize BDD  *********************** */
	//@Bean
	CommandLineRunner commandLineRunner(AppServiceImpl appService) {
		return args -> {
			
			appService.addUser("user1", "12345", "12345");
			appService.addUser("nasser", "12345", "12345");
			appService.addRole("USER");
			appService.addRole("ADMIN");
			appService.addRoleToUser("user1", "USER");
			appService.addRoleToUser("nasser", "ADMIN");
		};
	}

}