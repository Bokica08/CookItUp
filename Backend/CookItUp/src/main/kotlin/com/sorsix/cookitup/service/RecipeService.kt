package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.enumeration.DifficultyLevel

interface RecipeService {
    fun findAllByCustomer(customer: Customer) : List<Recipe>
    fun findAllByCategoryListContains(category: Category) : List<Recipe>
    fun findAllByDifficultyLevel(difficultyLevel: DifficultyLevel) : List<Recipe>
    fun findAllByNameContainingIgnoreCase(name: String) : List<Recipe>
    fun findAllByRecipe(recipe: Recipe) : List<Ingredient>
    fun findAllByIngredient(ingredient: Ingredient) : List<Recipe>
    fun getAll() : List<Recipe>
}