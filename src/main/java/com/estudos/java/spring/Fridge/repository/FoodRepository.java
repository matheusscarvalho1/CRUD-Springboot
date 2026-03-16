package com.estudos.java.spring.Fridge.repository;

import com.estudos.java.spring.Fridge.model.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<FoodModel,Long> {
}
