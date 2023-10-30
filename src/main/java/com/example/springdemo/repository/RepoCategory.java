package com.example.springdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdemo.entities.Category;

public interface RepoCategory extends JpaRepository<Category, Long> {

}
