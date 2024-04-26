package com.stefansSagoSkrift.ccpbackend.repositories;

import com.stefansSagoSkrift.ccpbackend.entities.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByCreationTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
}
