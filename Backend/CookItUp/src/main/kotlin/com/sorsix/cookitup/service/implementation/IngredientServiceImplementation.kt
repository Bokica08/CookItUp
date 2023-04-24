package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.repository.IngredientRepository
import com.sorsix.cookitup.service.IngredientService
import org.springframework.stereotype.Service

@Service
class IngredientServiceImplementation(private val  ingredientRepository: IngredientRepository):IngredientService {
    override fun getAllIngredients(): List<Ingredient> {
        return ingredientRepository.findAll()
    }
}