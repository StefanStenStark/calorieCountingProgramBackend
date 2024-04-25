package com.stefansSagoSkrift.ccpbackend.DTOs;

import lombok.Data;


@Data
public class FoodItemDTO {
    private Long id;
    private String name;
    private double calories;
    private double grams;
    private String type;
    private String nutritionRating;
}
