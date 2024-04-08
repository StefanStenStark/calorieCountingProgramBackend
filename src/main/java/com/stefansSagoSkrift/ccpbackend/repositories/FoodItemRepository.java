package com.stefansSagoSkrift.ccpbackend.repositories;

import com.stefansSagoSkrift.ccpbackend.entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
}
