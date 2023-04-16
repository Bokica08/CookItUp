package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.*
import com.sorsix.cookitup.model.dto.RecipeDTO
import com.sorsix.cookitup.model.dto.RecipeInfoDTO
import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import com.sorsix.cookitup.model.enumeration.Measure
import com.sorsix.cookitup.model.manyToMany.IngredientIsInRecipe
import com.sorsix.cookitup.model.manyToMany.IngredientIsInRecipeId
import com.sorsix.cookitup.repository.*
import com.sorsix.cookitup.service.RecipeService
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class RecipeServiceImplementation(
    private val recipeRepository: RecipeRepository,
    private val ingredientIsInRecipeRepository: IngredientIsInRecipeRepository,
    private val customerRepository: CustomerRepository,
    private val categoryRepository: CategoryRepository,
    private val ingredientRepository: IngredientRepository,
    private val imageRepository: ImageRepository
) : RecipeService{
    override fun findAllByCustomer(customer: Customer): List<RecipeInfoDTO> {
        return recipeRepository.findAllByCustomer(customer).map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }
    }

    override fun findAllByCategoryListContains(category: Category): List<RecipeInfoDTO> {
        return recipeRepository.findAll().filter { it.categoryList.contains(category) }.map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }
    }

    override fun findAllByDifficultyLevel(difficultyLevel: DifficultyLevel): List<RecipeInfoDTO> {
        return recipeRepository.findAllByDifficultyLevel(difficultyLevel).map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }
    }

    override fun findAllByNameContainingIgnoreCase(name: String): List<RecipeInfoDTO> {
        return recipeRepository.findAllByNameContainingIgnoreCase(name).map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }
    }

    override fun findAllByRecipe(recipe: Recipe): List<Ingredient> {
        return ingredientIsInRecipeRepository.findAllByRecipe(recipe).map {
            ingredientIsInRecipe ->  ingredientIsInRecipe.ingredient
        }.toList()
    }

    override fun findAllByIngredient(ingredient: Ingredient): List<RecipeInfoDTO> {
        return ingredientIsInRecipeRepository.findAllByIngredient(ingredient).map {
            ingredientIsInRecipe -> ingredientIsInRecipe.recipe
        }.map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }.toList()
    }

    override fun getAll(): List<RecipeInfoDTO> {
        return recipeRepository.findAll().map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }
    }

    override fun save(recipeDTO: RecipeDTO): Recipe {
        val categoryList: MutableList<Category> = recipeDTO.categoryList.map { cat ->
            categoryRepository.findByName(cat)
        }.toMutableList()
        val recipe = Recipe(
                null,
                recipeDTO.name,
                recipeDTO.description,
                recipeDTO.numPersons,
                DifficultyLevel.valueOf(recipeDTO.difficultyLevel),
                recipeDTO.prepTime, 0, 0, LocalDateTime.now(),
                customerRepository.findById(recipeDTO.customerId).get(),
                categoryList
        )
        recipeRepository.save(recipe)
        for(ing in recipeDTO.ingredientList){
            val ingredient: Ingredient = ingredientRepository.findByName(ing.name)
            ingredientIsInRecipeRepository.save(IngredientIsInRecipe(
                IngredientIsInRecipeId(ingredient.ingredientId!!,
                recipe.recipeId!!),
                ingredient,
                recipe,ing.quantity,
                Measure.valueOf(ing.measure)))
        }
        return recipe
    }

    override fun getDetailsForRecipe(id: Long): RecipeInfoDTO {
        val recipe = recipeRepository.getReferenceById(id)
        val ingredientList: List<Ingredient> = this.findAllByRecipe(recipe)
        val imageList: List<Image> = imageRepository.getAllByRecipe(recipe)
        return RecipeInfoDTO(
            recipe.recipeId,
            recipe.name,
            recipe.description,
            recipe.numPersons,
            recipe.difficultyLevel,
            recipe.prepTime,
            recipe.avRating,
            recipe.viewCount,
            recipe.createdOn,
            recipe.customer,
            recipe.categoryList,
            ingredientList,
            imageList
        )
    }

    override fun getRecipeById(id: Long): Recipe {
        return recipeRepository.getReferenceById(id)
    }

    override fun getNewestRecipes(): List<RecipeInfoDTO> {
        return recipeRepository.findAll().map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }.sortedByDescending { it.createdOn }.subList(0,5)
    }

    override fun getTopRatedRecipes(): List<RecipeInfoDTO> {
        return recipeRepository.findAll().map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }.sortedByDescending { it.avRating }.subList(0,5)
    }

    override fun getMostViewedRecipes(): List<RecipeInfoDTO> {
        return recipeRepository.findAll().map { recipe->this.getDetailsForRecipe(recipe.recipeId!!) }.sortedByDescending { it.viewCount }.subList(0,5)
    }
}