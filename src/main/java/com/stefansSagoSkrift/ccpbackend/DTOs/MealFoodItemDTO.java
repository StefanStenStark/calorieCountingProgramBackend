package com.stefansSagoSkrift.ccpbackend.DTOs;

import lombok.Data;

@Data
public class MealFoodItemDTO {
    private Long id;
    private Long mealId;
    private Long foodItemId;
    private double grams;
}
