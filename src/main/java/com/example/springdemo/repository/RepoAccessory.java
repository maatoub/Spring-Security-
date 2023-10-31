package com.example.springdemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdemo.entities.Accessory;

    public interface RepoAccessory extends JpaRepository<Accessory, Long> {
    public Page<Accessory> findByBrandContains(String mc, Pageable pageable);
    }
            