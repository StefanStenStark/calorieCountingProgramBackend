package com.stefansSagoSkrift.ccpbackend.service;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemDTO createFoodItem(FoodItem foodItem) {
        FoodItem newFoodItem = new FoodItem();
        newFoodItem.setName(foodItem.getName());
        newFoodItem.setCalories(foodItem.getCalories());

        foodItemRepository.save(newFoodItem);

        FoodItemDTO newFoodItemDTO = new FoodItemDTO();
        newFoodItemDTO.setCalories(foodItem.getCalories());
        newFoodItemDTO.setName(foodItem.getName());

        return newFoodItemDTO;
    }
}
