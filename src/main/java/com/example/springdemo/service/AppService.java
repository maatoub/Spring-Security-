package com.example.springdemo.service;

import com.example.springdemo.entities.AppRole;
import com.example.springdemo.entities.AppUser;

public interface AppService {
    AppUser addUser(String username, String password, String confirmPassword);

    AppRole addRole(String role);

    AppUser loadUserByUsername(String username);

    void addRoleToUser(String username, String role);

    void removeRoleFromUser(String username, String role);

}
