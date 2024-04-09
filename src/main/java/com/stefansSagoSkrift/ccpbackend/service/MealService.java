package com.stefansSagoSkrift.ccpbackend.service;

import com.stefansSagoSkrift.ccpbackend.DTOs.MealDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import com.stefansSagoSkrift.ccpbackend.repositories.MealRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final FoodItemRepository foodItemRepository;
    public MealDTO createMeal(Meal meal) {

        Meal newMeal = new Meal();
        newMeal.setName(meal.getName());
        mealRepository.save(newMeal);

        MealDTO mealInfo = new MealDTO();
        mealInfo.setName(meal.getName());

        return mealInfo;
    }

    public MealDTO addFoodItemToMeal(Long mealId, Long foodItemId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + mealId));
        FoodItem foodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new EntityNotFoundException("Food item not found with id: " + foodItemId));

        meal.getFoodItems().add(foodItem);
        mealRepository.save(meal);


        MealDTO mealInfo = new MealDTO();
        mealInfo.setFoodItems(meal.getFoodItems());
        return mealInfo;
    }
}
