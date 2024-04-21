package com.stefansSagoSkrift.ccpbackend.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MealFoodItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne
    @JoinColumn(name = "food_item_id")
    private FoodItem foodItem;

    private double grams;
}