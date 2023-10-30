package com.example.springdemo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.springdemo.entities.AppUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceUserDetailsImp implements UserDetailsService {

    private AppService appService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appService.loadUserByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User %s not found " + username);
        }
        String[] roles = appUser.getRoles().stream().map(u -> u.getRole()).toArray(String[]::new);

        UserDetails user = User.builder()
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .roles(roles)
                .build();
        return user;
    }

}
