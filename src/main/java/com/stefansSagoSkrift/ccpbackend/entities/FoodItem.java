package com.stefansSagoSkrift.ccpbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
public class FoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double calories;

    private double grams;

    private String type;

    private String nutritionRating;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;
}
