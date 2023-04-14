package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Ingredient
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientRepository : JpaRepository<Ingredient, Long> {
}