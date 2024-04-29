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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        newMeal.setCreationTime(LocalDateTime.now());
        mealRepository.save(newMeal);

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

        return convertToMealDTO(newMeal);
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
    public List<MealDTO> getMealsForDate(String whatKindOfTime) {
        LocalDate minDate = LocalDate.now();
        LocalDate maxDate = LocalDate.now();
        if (whatKindOfTime.equals("day")){
            minDate = LocalDate.now();
        }
        if (whatKindOfTime.equals("week")){
            minDate = maxDate.minusWeeks(1);;
        }
        if (whatKindOfTime.equals("month")){
            minDate = maxDate.minusMonths(1);
        }
        if (whatKindOfTime.equals("year")){
            minDate = maxDate.minusYears(1);
        }

        LocalDateTime startDateTime = LocalDateTime.of(minDate, LocalTime.MIN);
        LocalDateTime endDateTime = LocalDateTime.of(maxDate, LocalTime.MAX);

        List<Meal> meals = mealRepository.findByCreationTimeBetween(startDateTime, endDateTime);
        return meals.stream()
                .filter(meal -> !meal.getType().toLowerCase().contains("template"))
                .map(this::convertToMealDTO)
                .collect(Collectors.toList());
    }

    public void deleteMeal(Long mealId) {
        mealRepository.deleteById(mealId);
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
