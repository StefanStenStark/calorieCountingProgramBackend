package com.stefansSagoSkrift.ccpbackend.service;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    public FoodItemDTO createFoodItem(FoodItem foodItem) {
        FoodItem newFoodItem = new FoodItem();
        newFoodItem.setName(foodItem.getName());
        newFoodItem.setCalories(foodItem.getCalories());
        newFoodItem.setGrams(foodItem.getGrams());
        newFoodItem.setType(foodItem.getType());
        newFoodItem.setNutritionRating(foodItem.getNutritionRating());

        foodItemRepository.save(newFoodItem);

        FoodItemDTO newFoodItemDTO = convertFoodItemToDTO(newFoodItem);

        return newFoodItemDTO;
    }

    public List<FoodItemDTO> getAllFoodItemsTemplate() {
        List<FoodItem> foodItems = foodItemRepository.findAll();
        return foodItems.stream()
                .filter(meal -> "template".equals(meal.getType()))
                .map(this::convertFoodItemToDTO)
                .collect(Collectors.toList());
    }

    public List<FoodItemDTO> getAllTheFoodItems() {
           List<FoodItem> foodItems = foodItemRepository.findAll();
           return foodItems.stream()
                   .map(this::convertFoodItemToDTO)
                   .collect(Collectors.toList());
    }

    public void deleteFoodItem(Long foodItemId) {
        foodItemRepository.deleteById(foodItemId);
    }

    private FoodItemDTO convertFoodItemToDTO(FoodItem foodItems) {
        FoodItemDTO dto = new FoodItemDTO();
        dto.setId(foodItems.getId());
        dto.setName(foodItems.getName());
        dto.setCalories(foodItems.getCalories());
        dto.setGrams(foodItems.getGrams());
        dto.setType(foodItems.getType());
        dto.setNutritionRating(foodItems.getNutritionRating());
        return dto;
    }



}
