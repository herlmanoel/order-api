package com.orderapi.repositories;

import com.orderapi.entities.Category;
import com.orderapi.entities.Ingredient;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

}
