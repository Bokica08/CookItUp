package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.Review
import com.sorsix.cookitup.model.dto.IngredientDTO
import com.sorsix.cookitup.repository.IngredientIsInRecipeRepository
import com.sorsix.cookitup.repository.IngredientRepository
import com.sorsix.cookitup.service.IngredientService
import org.springframework.stereotype.Service

@Service
class IngredientServiceImplementation( val  ingredientRepository: IngredientRepository,
                                       val ingredientIsInRecipeRepository: IngredientIsInRecipeRepository):IngredientService {
    override fun getAllIngredients(): List<Ingredient> {
        return ingredientRepository.findAll()
    }

    override fun deleteAllByRecipe(recipe: Recipe): Any {
        val ingredientIsInRecipe= ingredientIsInRecipeRepository.findAllByRecipe(recipe)
        return ingredientIsInRecipeRepository.deleteAll(ingredientIsInRecipe)
    }

    override fun save(ingredientDTO: IngredientDTO): Ingredient {
     return ingredientRepository.save(Ingredient(null,ingredientDTO.name,ingredientDTO.description))
    }
}