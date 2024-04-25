package com.stefansSagoSkrift.ccpbackend.service;

import com.stefansSagoSkrift.ccpbackend.DTOs.FoodItemDTO;
import com.stefansSagoSkrift.ccpbackend.DTOs.MealDTO;
import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import com.stefansSagoSkrift.ccpbackend.repositories.MealRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;
    private final FoodItemRepository foodItemRepository;
    public MealDTO createMealWithFoodItems(MealDTO mealDTO) {
        Meal newMeal = new Meal();
        newMeal.setName(mealDTO.getName());
        newMeal.setType(mealDTO.getType());

        newMeal = mealRepository.save(newMeal);

        List<FoodItemDTO> foodItems = mealDTO.getFoodItemDTOS();

        List<FoodItem> savedFoodItems = new ArrayList<>();

        if (foodItems != null) {
            for (FoodItemDTO foodItemDTO : foodItems) {
                FoodItem newFoodItem = new FoodItem();
                newFoodItem.setName(foodItemDTO.getName());
                newFoodItem.setCalories(foodItemDTO.getCalories());
                newFoodItem.setGrams(foodItemDTO.getGrams());
                newFoodItem.setNutritionRating(foodItemDTO.getNutritionRating());

                newFoodItem.setMeal(newMeal);

                savedFoodItems.add(foodItemRepository.save(newFoodItem));
            }
        }
        MealDTO mealInfo = new MealDTO();
        mealInfo.setId(newMeal.getId());
        mealInfo.setName(newMeal.getName());
        mealInfo.setType(newMeal.getType());
        mealInfo.setFoodItemDTOS(savedFoodItems.stream()
                .map(this::convertToFoodItemDTO)
                .collect(Collectors.toList()));
        return mealInfo;
    }


    public MealDTO addFoodItemToMeal(Long mealId, FoodItem foodItem) {

        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new EntityNotFoundException("Meal not found with id: " + mealId));

        FoodItem theNewFoodItem = new FoodItem();
        theNewFoodItem.setName(foodItem.getName());
        theNewFoodItem.setCalories(foodItem.getCalories());
        theNewFoodItem.setGrams(foodItem.getGrams());
        theNewFoodItem.setMeal(meal);

        foodItemRepository.save(theNewFoodItem);
        mealRepository.save(meal);


        MealDTO mealInfo = new MealDTO();
        return mealInfo;
    }


    public List<MealDTO> getAllMealsWithFoodItemsTemplates() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream()
                .filter(meal -> "template".equals(meal.getType()))
                .map(this::convertToMealDTO)
                .collect(Collectors.toList());
    }

    public List<MealDTO> getAllMealsWithNoTemplate() {
        List<Meal> meals = mealRepository.findAll();
        return meals.stream()
                .filter(meal -> !meal.getType().toLowerCase().contains("template"))
                .map(this::convertToMealDTO)
                .collect(Collectors.toList());
    }

    public MealDTO getMealWithFoodItemsById(Long mealId) {
        Optional<Meal> optionalMeal = mealRepository.findById(mealId);
        if (optionalMeal.isPresent()) {
            Meal meal = optionalMeal.get();
            return convertToMealDTO(meal);
        } else {
            return null;
        }
    }

    private MealDTO convertToMealDTO(Meal meal) {
        MealDTO mealDTO = new MealDTO();
        mealDTO.setId(meal.getId());
        mealDTO.setName(meal.getName());
        mealDTO.setType(meal.getType());

        List<FoodItemDTO> foodItemDTOs = meal.getFoodItems().stream()
                .map(this::convertToFoodItemDTO)
                .collect(Collectors.toList());
        mealDTO.setFoodItemDTOS(foodItemDTOs);

        return mealDTO;
    }

    private FoodItemDTO convertToFoodItemDTO(FoodItem foodItem) {
        FoodItemDTO foodItemDTO = new FoodItemDTO();
        foodItemDTO.setId(foodItem.getId());
        foodItemDTO.setName(foodItem.getName());
        foodItemDTO.setCalories(foodItem.getCalories());
        foodItemDTO.setGrams(foodItem.getGrams());
        foodItemDTO.setNutritionRating(foodItem.getNutritionRating());
        return foodItemDTO;
    }
}
