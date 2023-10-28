package com.example.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdemo.entities.AppUser;

public interface RepoUser extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
