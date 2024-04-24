package com.stefansSagoSkrift.ccpbackend.DTOs;

import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import lombok.Data;import java.util.ArrayList;
import java.util.List;

@Data
public class MealDTO {
    private Long id;
    private String name;
    private String type;
    private List<FoodItemDTO> foodItemDTOS = new ArrayList<>();
}
