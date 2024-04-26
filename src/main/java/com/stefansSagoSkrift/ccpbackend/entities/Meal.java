package com.stefansSagoSkrift.ccpbackend.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    private List<FoodItem> foodItems = new ArrayList<>();

    private String type;

    private LocalDateTime creationTime;
}


