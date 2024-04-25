package com.stefansSagoSkrift.ccpbackend.controllers;

import com.stefansSagoSkrift.ccpbackend.DTOs.MealDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class MealController {

    @Autowired
    private MealService mealService;

    @PostMapping("/createMealWithFoodItems")
    public ResponseEntity<MealDTO> createMealWithFoodItems(@RequestBody MealDTO meal){
        MealDTO createMeal = mealService.createMealWithFoodItems(meal);
        return ResponseEntity.ok().body(createMeal);
    }

    @PostMapping("/{mealId}/addFoodItem")
    public ResponseEntity<MealDTO> addFoodItemToMeal(
            @PathVariable Long mealId,
            @RequestBody FoodItem foodItem
            ){
        MealDTO mealDTO = mealService.addFoodItemToMeal(mealId, foodItem);
        return ResponseEntity.ok().body(mealDTO);
    }



    @GetMapping("/mealWithFoodItems/{mealId}")
    public ResponseEntity<MealDTO> getMealWithFoodItems(@PathVariable Long mealId) {
        MealDTO mealDTO = mealService.getMealWithFoodItemsById(mealId);
        if (mealDTO != null) {
            return ResponseEntity.ok(mealDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/allMealsWithTemplate")
    public ResponseEntity<List<MealDTO>> getAllMealsWithFoodItemsTemplates() {
        List<MealDTO> meals = mealService.getAllMealsWithFoodItemsTemplates();
        return ResponseEntity.ok(meals);
    }

    @GetMapping("/allMealsWithNoTemplate")
    public ResponseEntity<List<MealDTO>> getAllMealsWithNoTemplate() {
        List<MealDTO> meals = mealService.getAllMealsWithNoTemplate();
        return ResponseEntity.ok(meals);
    }




}
