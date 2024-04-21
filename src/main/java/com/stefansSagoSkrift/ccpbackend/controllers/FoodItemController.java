package com.stefansSagoSkrift.ccpbackend.controllers;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
}
