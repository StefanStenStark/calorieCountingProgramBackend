package com.stefansSagoSkrift.ccpbackend.service;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.DTOs.MealDTO;
import com.stefansSagoSkrift.ccpbackend.DTOs.MealFoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.entities.MealFoodItem;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import com.stefansSagoSkrift.ccpbackend.repositories.MealFoodItemRepository;
import com.stefansSagoSkrift.ccpbackend.repositories.MealRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final FoodItemRepository foodItemRepository;
    private final MealFoodItemRepository mealFoodItemRepository;
    public MealDTO createMeal(Meal meal) {

        Meal newMeal = new Meal();
        newMeal.setName(meal.getName());
        mealRepository.save(newMeal);

        MealDTO mealInfo = new MealDTO();
        mealInfo.setName(meal.getName());

        return mealInfo;
    }

    public MealDTO addFoodItemToMeal(Long mealId, Long foodItemId, double grams) {

        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + mealId));
        FoodItem foodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new EntityNotFoundException("Food item not found with id: " + foodItemId));

        MealFoodItem mealFoodItem = new MealFoodItem();
        mealFoodItem.setMeal(meal);
        mealFoodItem.setFoodItem(foodItem);
        mealFoodItem.setGrams(grams);


        meal.getMealFoodItems().add(mealFoodItem);


        mealRepository.save(meal);

        MealDTO mealInfo = new MealDTO();
        return mealInfo;
    }

    public List<MealDTO> getAllMealDTOs() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MealDTO convertToDTO(Meal meal) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setName(meal.getName());

        List<FoodItem> foodItems = meal.getMealFoodItems().stream()
                .map(MealFoodItem::getFoodItem)
                .collect(Collectors.toList());
        mealDTO.setFoodItems(foodItems);
        return mealDTO;
    }

    public List<MealFoodItemDTO> getAllMealFoodItemDTOs() {
        List<MealFoodItem> mealFoodItems = mealFoodItemRepository.findAll();
        return mealFoodItems.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MealFoodItemDTO convertToDTO(MealFoodItem mealFoodItem) {
        MealFoodItemDTO dto = new MealFoodItemDTO();
        dto.setId(mealFoodItem.getId());
        dto.setMealId(mealFoodItem.getMeal().getId());
        dto.setFoodItemId(mealFoodItem.getFoodItem().getId());
        dto.setGrams(mealFoodItem.getGrams());
        return dto;
    }
}
