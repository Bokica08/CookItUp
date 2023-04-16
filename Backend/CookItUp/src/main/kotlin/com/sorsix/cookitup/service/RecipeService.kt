package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.dto.RecipeDTO
import com.sorsix.cookitup.model.dto.RecipeInfoDTO
import com.sorsix.cookitup.model.enumeration.DifficultyLevel

interface RecipeService {
    fun findAllByCustomer(customer: Customer) : List<RecipeInfoDTO>
    fun findAllByCategoryListContains(category: Category) : List<RecipeInfoDTO>
    fun findAllByDifficultyLevel(difficultyLevel: DifficultyLevel) : List<RecipeInfoDTO>
    fun findAllByNameContainingIgnoreCase(name: String) : List<RecipeInfoDTO>
    fun findAllByRecipe(recipe: Recipe) : List<Ingredient>
    fun findAllByIngredient(ingredient: Ingredient) : List<RecipeInfoDTO>
    fun getAll() : List<RecipeInfoDTO>
    fun save(recipeDTO: RecipeDTO) : Recipe
    fun getDetailsForRecipe(id: Long) : RecipeInfoDTO
    fun getRecipeById(id: Long) : Recipe
    fun getNewestRecipes() : List<RecipeInfoDTO>
    fun getTopRatedRecipes() : List<RecipeInfoDTO>
    fun getMostViewedRecipes() : List<RecipeInfoDTO>
}