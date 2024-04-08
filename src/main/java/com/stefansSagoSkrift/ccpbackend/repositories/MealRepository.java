package com.stefansSagoSkrift.ccpbackend.repositories;

import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
