package com.stefansSagoSkrift.ccpbackend.controllers;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping("/createFoodItem")
    public ResponseEntity<FoodItemDTO> createFoodItem(@RequestBody FoodItem foodItem){
        FoodItemDTO createdFoodItem = foodItemService.createFoodItem(foodItem);
        return ResponseEntity.ok().body(createdFoodItem);
    }
}
