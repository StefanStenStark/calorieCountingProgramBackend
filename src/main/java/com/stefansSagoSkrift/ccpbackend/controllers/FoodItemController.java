package com.stefansSagoSkrift.ccpbackend.controllers;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/createFoodItem")
    public ResponseEntity<FoodItemDTO> createFoodItem(@RequestBody FoodItem foodItem){
        FoodItemDTO createdFoodItem = foodItemService.createFoodItem(foodItem);
        return ResponseEntity.ok().body(createdFoodItem);
    }

    @PostMapping("/updateFoodItem/{mealId}")
    public ResponseEntity<FoodItemDTO> updateFoodItem(@RequestBody FoodItem foodItem, @PathVariable Long mealId){
        FoodItemDTO updateFoodItem = foodItemService.updateFoodItem(foodItem, mealId);
        return ResponseEntity.ok().body(updateFoodItem);
    }

    @GetMapping("/getAllFoodItems")
    public ResponseEntity<List<FoodItemDTO>> getAllTheFoodItems(){
        List<FoodItemDTO> foodItemDTO = foodItemService.getAllTheFoodItems();
        return ResponseEntity.ok().body(foodItemDTO);
    }

    @DeleteMapping("/deleteFoodItem/{mealId}/{foodItemId}")
    public ResponseEntity<?> deleteFoodItem(@PathVariable Long mealId, @PathVariable Long foodItemId) {
        try {
            foodItemService.deleteFoodItem(mealId, foodItemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error deleting food item: " + e.getMessage());
        }
    }
}
