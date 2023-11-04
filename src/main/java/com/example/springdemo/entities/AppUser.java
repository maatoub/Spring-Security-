package com.example.springdemo.entities;

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
import lombok.NoArgsConstructor;

@NoArgsConstructor
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
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AppRole> roles;
}
