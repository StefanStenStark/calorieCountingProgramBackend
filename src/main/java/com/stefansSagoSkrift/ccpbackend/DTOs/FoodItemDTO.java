package com.stefansSagoSkrift.ccpbackend.DTOs;

import lombok.Data;

@Data
public class FoodItemDTO {
    private String name;
    private double calories;
    private String nutritionRating;
}