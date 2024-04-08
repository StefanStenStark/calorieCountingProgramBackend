package com.stefansSagoSkrift.ccpbackend.controllers;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.DTOs.MealDTO;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping("/createMeal")
    public ResponseEntity<MealDTO> createMeal(@RequestBody Meal meal){
        MealDTO createMeal = mealService.createMeal(meal);
        return ResponseEntity.ok().body(createMeal);
    }
}
