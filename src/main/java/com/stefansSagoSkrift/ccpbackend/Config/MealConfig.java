package com.stefansSagoSkrift.ccpbackend.Config;

import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import com.stefansSagoSkrift.ccpbackend.repositories.FoodItemRepository;
import com.stefansSagoSkrift.ccpbackend.repositories.MealRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class MealConfig {

    private final FoodItemRepository foodItemRepository;
    private final MealRepository mealRepository;
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            createFoodItemsAndMeals();
        };
    }

    private void createFoodItemsAndMeals() {
        FoodItem foodItem1 = new FoodItem();
        foodItem1.setName("Tofu");
        foodItem1.setCalories(94);
        foodItem1.setGrams(100);
        foodItem1.setNutritionRating("High");
        foodItem1.setType("template");
        foodItemRepository.save(foodItem1);

        FoodItem foodItem2 = new FoodItem();
        foodItem2.setName("Quinoa");
        foodItem2.setCalories(120);
        foodItem2.setGrams(100);
        foodItem2.setNutritionRating("High");
        foodItem2.setType("template");
        foodItemRepository.save(foodItem2);

        FoodItem foodItem3 = new FoodItem();
        foodItem3.setName("Spinach");
        foodItem3.setCalories(23);
        foodItem3.setGrams(100);
        foodItem3.setNutritionRating("High");
        foodItem3.setType("template");
        foodItemRepository.save(foodItem3);


        //Create meals
        //First meal
        Meal meal1 = new Meal();
        meal1.setName("Tofu Meal");
        meal1.setType("template");
        mealRepository.save(meal1);

        List<FoodItem> foodItemsMeal1 = new ArrayList<>();
        foodItemsMeal1.add(foodItem1);
        foodItemsMeal1.add(foodItem2);

        List<FoodItem> foodItemsForMeal1 = copyFoodItems(foodItemsMeal1, meal1);

        meal1.setFoodItems(foodItemsForMeal1);
        mealRepository.save(meal1);

        //Second meal
        Meal meal2 = new Meal();
        meal2.setName("Rice n stuff");
        meal2.setType("template");
        mealRepository.save(meal2);

        List<FoodItem> foodItemsMeal2 = new ArrayList<>();
        foodItemsMeal2.add(foodItem2);
        foodItemsMeal2.add(foodItem3);

        List<FoodItem> foodItemsForMeal2 = copyFoodItems(foodItemsMeal2, meal2);

        meal2.setFoodItems(foodItemsForMeal2);
        mealRepository.save(meal2);

        //Third meal
        Meal meal3 = new Meal();
        meal3.setName("Tofu Meal");
        meal3.setType("template");
        mealRepository.save(meal3);

        List<FoodItem> foodItemsMeal3 = new ArrayList<>();
        foodItemsMeal3.add(foodItem1);

        List<FoodItem> foodItemsForMeal3 = copyFoodItems(foodItemsMeal3, meal3);

        meal3.setFoodItems(foodItemsForMeal3);
        mealRepository.save(meal3);
    }

    private List<FoodItem> copyFoodItems(List<FoodItem> foodItems, Meal meal) {
        List<FoodItem> copiedFoodItems = new ArrayList<>();

        for (FoodItem foodItem : foodItems) {
            FoodItem copiedFoodItem = new FoodItem();
            copiedFoodItem.setName(foodItem.getName());
            copiedFoodItem.setCalories(foodItem.getCalories());
            copiedFoodItem.setGrams(foodItem.getGrams());
            copiedFoodItem.setNutritionRating(foodItem.getNutritionRating());
            copiedFoodItem.setType("normal");
            copiedFoodItem.setMeal(meal);
            copiedFoodItems.add(copiedFoodItem);
        }

        return copiedFoodItems;
    }


}
