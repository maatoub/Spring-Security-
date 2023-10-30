package com.example.springdemo.entities;

import org.hibernate.annotations.ManyToAny;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccessory;
    private String brand;
    private double price;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
}
