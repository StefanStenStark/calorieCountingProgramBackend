package com.stefansSagoSkrift.ccpbackend.repositories;

import com.stefansSagoSkrift.ccpbackend.entities.MealFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MealFoodItemRepository extends JpaRepository<MealFoodItem, Long> {
}
