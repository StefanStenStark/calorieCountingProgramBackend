package com.stefansSagoSkrift.ccpbackend.service;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import com.stefansSagoSkrift.ccpbackend.repositories.MealRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final MealRepository mealRepository;

    public FoodItemDTO createFoodItem(FoodItem foodItem) {
        FoodItem newFoodItem = new FoodItem();
        newFoodItem.setName(foodItem.getName());
        newFoodItem.setCalories(foodItem.getCalories());
        newFoodItem.setGrams(foodItem.getGrams());
        newFoodItem.setNutritionRating(foodItem.getNutritionRating());

        foodItemRepository.save(newFoodItem);

        FoodItemDTO newFoodItemDTO = new FoodItemDTO();
        newFoodItemDTO.setCalories(foodItem.getCalories());
        newFoodItemDTO.setName(foodItem.getName());
        newFoodItemDTO.setGrams(foodItem.getGrams());
        newFoodItemDTO.setNutritionRating(foodItem.getNutritionRating());

        return newFoodItemDTO;
    }

    public FoodItemDTO updateFoodItem(FoodItem foodItem, Long foodId) {
        FoodItem updatedFoodItem = foodItemRepository.findById(foodId)
                .orElseThrow(() -> new EntityNotFoundException("Did not find item with id: " + foodId));

        updatedFoodItem.setName(foodItem.getName());
        updatedFoodItem.setCalories(foodItem.getCalories());
        updatedFoodItem.setGrams(foodItem.getGrams());
        updatedFoodItem.setNutritionRating(foodItem.getNutritionRating());

        foodItemRepository.save(updatedFoodItem);

        FoodItemDTO updatedFoodItemInfo = new FoodItemDTO();

        updatedFoodItemInfo.setName(updatedFoodItem.getName());
        updatedFoodItemInfo.setCalories(updatedFoodItem.getCalories());
        updatedFoodItemInfo.setNutritionRating(updatedFoodItem.getNutritionRating());
        return updatedFoodItemInfo;


    }

    public List<FoodItemDTO> getAllTheFoodItems() {
           List<FoodItem> foodItems = foodItemRepository.findAll();
           return foodItems.stream()
                   .map(this::convertFoodItemToDTO)
                   .collect(Collectors.toList());
    }

    private FoodItemDTO convertFoodItemToDTO(FoodItem foodItems) {
        FoodItemDTO dto = new FoodItemDTO();
        dto.setName(foodItems.getName());
        dto.setCalories(foodItems.getCalories());
        dto.setNutritionRating(foodItems.getNutritionRating());
        return dto;
    }

    public void deleteFoodItem(Long mealId, Long foodItemId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + mealId));

        meal.getFoodItems().removeIf(item -> item.getId().equals(foodItemId));
        mealRepository.save(meal);
        foodItemRepository.deleteById(foodItemId);
    }

}
