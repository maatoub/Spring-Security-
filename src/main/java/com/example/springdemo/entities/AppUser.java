package com.example.springdemo.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
@Entity
public class AppUser {
    @Id
    private String userId;
    @Column(unique = true)
    @NotEmpty
    @Size(min = 4, max = 12)
    private String username;
    @NotEmpty
    @Size(min = 4, max = 12)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;

    public AppUser() {
        this.roles = new ArrayList<>();
    }
}
