package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import com.sorsix.cookitup.repository.IngredientIsInRecipeRepository
import com.sorsix.cookitup.repository.RecipeRepository
import com.sorsix.cookitup.service.RecipeService
import org.springframework.stereotype.Service

@Service
class RecipeServiceImplementation(
    private val recipeRepository: RecipeRepository,
    private val ingredientIsInRecipeRepository: IngredientIsInRecipeRepository
) : RecipeService{
    override fun findAllByCustomer(customer: Customer): List<Recipe> {
        return recipeRepository.findAllByCustomer(customer)
    }

    override fun findAllByCategoryListContains(category: Category): List<Recipe> {
        return recipeRepository.findAll().filter { it.categoryList.contains(category) }
    }

    override fun findAllByDifficultyLevel(difficultyLevel: DifficultyLevel): List<Recipe> {
        return recipeRepository.findAllByDifficultyLevel(difficultyLevel)
    }

    override fun findAllByNameContainingIgnoreCase(name: String): List<Recipe> {
        return recipeRepository.findAllByNameContainingIgnoreCase(name)
    }

    override fun findAllByRecipe(recipe: Recipe): List<Ingredient> {
        return ingredientIsInRecipeRepository.findAllByRecipe(recipe).map {
            ingredientIsInRecipe ->  ingredientIsInRecipe.ingredient
        }.toList()
    }

    override fun findAllByIngredient(ingredient: Ingredient): List<Recipe> {
        return ingredientIsInRecipeRepository.findAllByIngredient(ingredient).map {
            ingredientIsInRecipe -> ingredientIsInRecipe.recipe
        }.toList()
    }

    override fun getAll(): List<Recipe> {
        return recipeRepository.findAll()
    }
}