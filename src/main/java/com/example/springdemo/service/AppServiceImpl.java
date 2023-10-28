package com.example.springdemo.service;

import java.util.UUID;

import javax.management.RuntimeErrorException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdemo.entities.AppRole;
import com.example.springdemo.entities.AppUser;
import com.example.springdemo.repository.RepoRole;
import com.example.springdemo.repository.RepoUser;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AppServiceImpl implements AppService {

    private RepoUser repoUser;
    private RepoRole repoRole;
    private PasswordEncoder pEncoder;

    @Override
    public AppUser addUser(String username, String password, String confirmPassword) {
        AppUser user = repoUser.findByUsername(username);
        if (user != null) {
            throw new RuntimeException("this user already exist");
        }

        if (!password.equals(confirmPassword)) {
            throw new RuntimeException("Passwords not match");
        }
        user = AppUser.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(pEncoder.encode(password))
                .build();
        AppUser userSaved = repoUser.save(user);
        return userSaved;
    }

    @Override
    public AppRole addRole(String role) {
        AppRole appRole = repoRole.findById(role).orElse(null);
        if (appRole != null) {
            throw new RuntimeException("this role already exist");
        }
        appRole = AppRole
                .builder()
                .role(role)
                .build();
        return repoRole.save(appRole);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser appUser = repoUser.findByUsername(username);
        AppRole appRole = repoRole.findById(role).get();
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        AppUser appUser = repoUser.findByUsername(username);
        AppRole appRole = repoRole.findById(role).get();
        appUser.getRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {

        return repoUser.findByUsername(username);
    }

}