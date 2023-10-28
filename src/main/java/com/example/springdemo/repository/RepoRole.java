package com.example.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdemo.entities.AppRole;

public interface RepoRole extends JpaRepository<AppRole, String> {

}
