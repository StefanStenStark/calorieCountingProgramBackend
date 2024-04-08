package com.stefansSagoSkrift.ccpbackend.service;

import com.stefansSagoSkrift.ccpbackend.DTOs.MealDTO;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import com.stefansSagoSkrift.ccpbackend.repositories.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    public MealDTO createMeal(Meal meal) {

        Meal newMeal = new Meal();
        newMeal.setName(meal.getName());
        mealRepository.save(newMeal);

        MealDTO mealInfo = new MealDTO();
        mealInfo.setName(meal.getName());

        return mealInfo;
    }
}
