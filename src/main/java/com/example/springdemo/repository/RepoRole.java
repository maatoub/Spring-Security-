package com.example.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdemo.entities.AppRole;
import java.util.List;


public interface RepoRole extends JpaRepository<AppRole, String> {
   List<AppRole> findByRole(String role);
}
