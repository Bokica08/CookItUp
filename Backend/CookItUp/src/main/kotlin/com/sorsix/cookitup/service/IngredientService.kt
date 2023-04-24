package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Ingredient

interface IngredientService {
    fun getAllIngredients():List<Ingredient>
}