package com.stefansSagoSkrift.ccpbackend.controllers;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.DTOs.MealDTO;
import com.stefansSagoSkrift.ccpbackend.DTOs.MealFoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.entities.MealFoodItem;
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

    @PostMapping("/createMeal")
    public ResponseEntity<MealDTO> createMeal(@RequestBody Meal meal){
        MealDTO createMeal = mealService.createMeal(meal);
        return ResponseEntity.ok().body(createMeal);
    }

    @PostMapping("/{mealId}/addFoodItem")
    public ResponseEntity<MealDTO> addFoodItemToMeal(
            @PathVariable Long mealId,
            @RequestParam Long foodItemId,
            @RequestParam double grams
    ){
        MealDTO mealDTO = mealService.addFoodItemToMeal(mealId, foodItemId, grams);
        return ResponseEntity.ok().body(mealDTO);
    }

    @GetMapping("/meals")
    public ResponseEntity<List<MealDTO>> getAllMeals() {
        List<MealDTO> mealDTOs = mealService.getAllMealDTOs();
        return ResponseEntity.ok().body(mealDTOs);
    }

    @GetMapping("/mealFoodItems")
    public ResponseEntity<List<MealFoodItemDTO>> getAllMealFoodItems() {
        List<MealFoodItemDTO> dtos = mealService.getAllMealFoodItemDTOs();
        return ResponseEntity.ok().body(dtos);
    }
}
