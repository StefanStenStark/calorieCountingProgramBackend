package com.stefansSagoSkrift.ccpbackend.service;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import jakarta.persistence.EntityNotFoundException;
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
        newFoodItem.setNutritionRating(foodItem.getNutritionRating());

        foodItemRepository.save(newFoodItem);

        FoodItemDTO newFoodItemDTO = new FoodItemDTO();
        newFoodItemDTO.setCalories(foodItem.getCalories());
        newFoodItemDTO.setName(foodItem.getName());
        newFoodItemDTO.setNutritionRating(foodItem.getNutritionRating());

        return newFoodItemDTO;
    }

    public FoodItemDTO updateFoodItem(FoodItem foodItem, Long mealId) {
        FoodItem updatedFoodItem = foodItemRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException("Did not find item with id: " + mealId));

        updatedFoodItem.setName(foodItem.getName());
        updatedFoodItem.setCalories(foodItem.getCalories());
        updatedFoodItem.setNutritionRating(foodItem.getNutritionRating());

        foodItemRepository.save(updatedFoodItem);

        FoodItemDTO updatedFoodItemInfo = new FoodItemDTO();

        updatedFoodItemInfo.setName(updatedFoodItem.getName());
        updatedFoodItemInfo.setCalories(updatedFoodItem.getCalories());
        updatedFoodItemInfo.setNutritionRating(updatedFoodItem.getNutritionRating());
        return updatedFoodItemInfo;


    }
}
