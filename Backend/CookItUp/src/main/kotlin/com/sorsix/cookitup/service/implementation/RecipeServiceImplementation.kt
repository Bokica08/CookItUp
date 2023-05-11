package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.*
import com.sorsix.cookitup.model.dto.*
import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import com.sorsix.cookitup.model.enumeration.Measure
import com.sorsix.cookitup.model.manyToMany.IngredientIsInRecipe
import com.sorsix.cookitup.model.manyToMany.IngredientIsInRecipeId
import com.sorsix.cookitup.repository.*
import com.sorsix.cookitup.service.RecipeService
import com.sorsix.cookitup.service.ReviewService
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class RecipeServiceImplementation(
     val recipeRepository: RecipeRepository,
     val ingredientIsInRecipeRepository: IngredientIsInRecipeRepository,
     val categoryRepository: CategoryRepository,
     val ingredientRepository: IngredientRepository,
     val imageRepository: ImageRepository,
     val reviewService: ReviewService,

) : RecipeService {
    override fun findAllByCustomer(customer: Customer): List<RecipePreviewDTO> {
        return recipeRepository.findAllByCustomer(customer)
            .map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
    }

    override fun findAllByCategoryListContains(category: Category): List<RecipePreviewDTO> {
        return recipeRepository.findAll().filter { it.categoryList.contains(category) }
            .map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
    }

    override fun findAllByDifficultyLevel(difficultyLevel: DifficultyLevel): List<RecipePreviewDTO> {
        return recipeRepository.findAllByDifficultyLevel(difficultyLevel)
            .map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
    }

    override fun findAllByNameContainingIgnoreCase(name: String): List<RecipePreviewDTO> {
        return recipeRepository.findAllByNameContainingIgnoreCase(name)
            .map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
    }

    override fun findAllByRecipe(recipe: Recipe): List<Ingredient> {
        return ingredientIsInRecipeRepository.findAllByRecipe(recipe).map { ingredientIsInRecipe ->
            ingredientIsInRecipe.ingredient
        }.toList()
    }

    override fun findAllByIngredient(ingredient: Ingredient): List<RecipeInfoDTO> {
        return ingredientIsInRecipeRepository.findAllByIngredient(ingredient).map { ingredientIsInRecipe ->
            ingredientIsInRecipe.recipe
        }.map { recipe -> this.getDetailsForRecipe(recipe.recipeId!!) }.toList()
    }

    override fun getAll(): List<RecipePreviewDTO> {
        return recipeRepository.findAll().map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
    }

    override fun save(recipeDTO: RecipeDTO, customer: Customer): Recipe {
        val categoryList: MutableList<Category> = recipeDTO.categoryList.map { cat ->
            categoryRepository.findByNameIgnoreCase(cat)
        }.toMutableList()
        val recipe = Recipe(
            null,
            recipeDTO.name,
            recipeDTO.description,
            recipeDTO.numPersons,
            DifficultyLevel.valueOf(recipeDTO.difficultyLevel),
            recipeDTO.prepTime, 0.0, 0, LocalDateTime.now(),
            customer,
            categoryList
        )
        recipeRepository.save(recipe)
        for (ing in recipeDTO.ingredientList) {
            val ingredient: Ingredient = ingredientRepository.findByName(ing.name)
            ingredientIsInRecipeRepository.save(
                IngredientIsInRecipe(
                    IngredientIsInRecipeId(
                        ingredient.ingredientId!!,
                        recipe.recipeId!!
                    ),
                    ingredient,
                    recipe, ing.quantity,
                    Measure.valueOf(ing.measure)
                )
            )
        }
        return recipe
    }

    override fun save(recipe: Recipe): Recipe {
        return recipeRepository.save(recipe)
    }

    override fun getDetailsForRecipe(id: Long): RecipeInfoDTO {
        val recipe = recipeRepository.getReferenceById(id)
        recipe.viewCount = recipe.viewCount?.plus(1)
        recipeRepository.save(recipe)
        val ingredientList: List<Ingredient> = this.findAllByRecipe(recipe)
        val ingredientIsInRecipeList: List<IngredientIsInRecipeDTO> = ingredientList.map { ing ->
            this.getIngredientInRecipe(id, ing.ingredientId!!)
        }
        val imageList: List<Image> = imageRepository.getAllByRecipe(recipe)
        val reviewList: List<ReviewForRecipeDTO> = reviewService.findAllByRecipe(recipe).map {
            reviewService.getReviewInfo(it.reviewId!!)
        }
        return RecipeInfoDTO(
            recipe.recipeId,
            recipe.name,
            recipe.description,
            recipe.numPersons,
            recipe.difficultyLevel,
            recipe.prepTime,
            recipe.avRating,
            recipe.viewCount,
            recipe.createdOn!!.toLocalDate(),
            recipe.customer,
            recipe.categoryList,
            ingredientIsInRecipeList,
            imageList,
            reviewList
        )
    }

    override fun getRecipeById(id: Long): Recipe {
        return recipeRepository.getReferenceById(id)
    }

    override fun getNewestRecipes(): List<RecipePreviewDTO> {
        return recipeRepository.findAll().sortedByDescending { it.createdOn }.subList(0, 5)
            .map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
    }

    override fun getTopRatedRecipes(): List<RecipePreviewDTO> {
        return recipeRepository.findAll().sortedByDescending { it.avRating }.subList(0, 5)
            .map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
    }

    override fun getMostViewedRecipes(): List<RecipePreviewDTO> {
        return recipeRepository.findAll().sortedByDescending { it.viewCount }.subList(0, 5)
            .map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
    }

    override fun getPreviewForRecipe(id: Long): RecipePreviewDTO {
        val recipe = recipeRepository.getReferenceById(id)
        val imageList: List<Image> = imageRepository.getAllByRecipe(recipe)
        return RecipePreviewDTO(
            recipe.recipeId,
            recipe.name,
            recipe.numPersons,
            recipe.difficultyLevel,
            recipe.prepTime,
            recipe.avRating,
            recipe.viewCount,
            recipe.createdOn!!.toLocalDate(),
            recipe.customer!!.username,
            imageList
        )
    }

    override fun getNumberOfRecipes(): Long {
        return recipeRepository.count()
    }

    override fun deleteRecipe(recipe: Recipe): Any {
        return recipeRepository.delete(recipe)
    }

    override fun getIngredientInRecipe(recipeId: Long, ingredientId: Long): IngredientIsInRecipeDTO {
        return ingredientIsInRecipeRepository.findById(IngredientIsInRecipeId(ingredientId, recipeId)).map {
            IngredientIsInRecipeDTO(it.ingredient.name!!, it.quantity, it.measure.toString())
        }.get()
    }

    override fun getFilteredRecipes(
        category: String?,
        inputText: String?,
        difficultyLevels: String?,
        prepTimes: String?,
        username: String?
    ): List<RecipePreviewDTO> {
        var firstFilter = recipeRepository.findAll().map { this.getPreviewForRecipe(it.recipeId!!) }
        if (category != null) {
            val categoryObject = categoryRepository.findByNameIgnoreCase(category)
            firstFilter = recipeRepository.findAll().filter { it.categoryList.contains(categoryObject) }
                .map { recipe -> this.getPreviewForRecipe(recipe.recipeId!!) }
        }
        var secondFilter = firstFilter
        if (inputText != null) {
            secondFilter = firstFilter.filter { it.name!!.contains(inputText, ignoreCase = true) }
        }
        var thirdFilter = secondFilter
        if (difficultyLevels != null) {
            thirdFilter = secondFilter.filter { it.difficultyLevel!!.toString() == difficultyLevels }
        }
        var fourthFilter = thirdFilter
        if (username != null) {
            fourthFilter = thirdFilter.filter { it.customerName!! == username }
        }
        return when (prepTimes) {
            "Under 30 minutes" -> {
                fourthFilter.filter { it.prepTime!! < 30 }
            }

            "30 to 60 minutes" -> {
                fourthFilter.filter { it.prepTime!! >= 30 && it.prepTime <= 60 }
            }

            "Over 60 minutes" -> {
                fourthFilter.filter { it.prepTime!! > 60 }
            }

            else -> {
                fourthFilter
            }
        }
    }

    override fun getSimilarRecipes(id: Long): List<RecipePreviewDTO> {
        val recipe = recipeRepository.getReferenceById(id)
        val allRecipes = recipeRepository.findAll().filter { it.recipeId != id }
        val map = mutableMapOf<Recipe, Double>()
        for (r in allRecipes) {
            val mutualCategories: ArrayList<Category> = ArrayList(recipe.categoryList)
            mutualCategories.retainAll(r.categoryList.toSet())
            val mutualIngredients: ArrayList<Ingredient> = ArrayList(this.getIngredientsInRecipe(id))
            mutualIngredients.retainAll(this.getIngredientsInRecipe(r.recipeId!!).toSet())
            val categoryCoef = (mutualCategories.size.toDouble() / recipe.categoryList.size.toDouble()) * 0.5
            val ingredientCoef =
                (mutualIngredients.size.toDouble() / this.getIngredientsInRecipe(id).size.toDouble()) * 0.5
            val similarityCoef = categoryCoef + ingredientCoef
            map[r] = similarityCoef
        }
        val sortedMap = map.entries.sortedByDescending { it.value }.associate { it.toPair() }
        return sortedMap.entries.take(5).map { it.key }.map { getPreviewForRecipe(it.recipeId!!) };
    }

    override fun getIngredientsInRecipe(id: Long): List<Ingredient> {
        return ingredientIsInRecipeRepository.findAllByRecipe(
            recipeRepository.getReferenceById(id)
        ).map { it.ingredient };
    }

    override fun edit(recipeDTO: EditRecipeDTO, id: Long): Recipe {
        val recipe = recipeRepository.findById(id).orElseThrow()
        recipe.name = recipeDTO.name
        recipe.numPersons = recipeDTO.numPersons
        recipe.prepTime = recipeDTO.prepTime
        recipe.difficultyLevel = DifficultyLevel.valueOf(recipeDTO.difficultyLevel)
        recipe.description = recipeDTO.description
        recipe.categoryList = recipeDTO.categoryList.toMutableList()
        for (ing in recipeDTO.ingredientList) {
            val ingredient: Ingredient = ingredientRepository.findByName(ing.name)
            ingredientIsInRecipeRepository.save(
                IngredientIsInRecipe(
                    IngredientIsInRecipeId(
                        ingredient.ingredientId!!,
                        recipe.recipeId!!
                    ),
                    ingredient,
                    recipe, ing.quantity,
                    Measure.valueOf(ing.measure)
                )
            )
        }
        recipeRepository.save(recipe)
    return recipe
    }
}