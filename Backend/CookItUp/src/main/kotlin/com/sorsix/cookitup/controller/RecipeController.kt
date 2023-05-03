package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Image
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.Review
import com.sorsix.cookitup.model.dto.RecipeDTO
import com.sorsix.cookitup.model.dto.RecipeInfoDTO
import com.sorsix.cookitup.model.dto.RecipePreviewDTO
import com.sorsix.cookitup.model.dto.ReviewDTO
import com.sorsix.cookitup.repository.CategoryRepository
import com.sorsix.cookitup.repository.ImageRepository
import com.sorsix.cookitup.repository.RecipeRepository
import com.sorsix.cookitup.service.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/recipe")

class RecipeController(val recipeService: RecipeService, val imageRepository: ImageRepository, val reviewService: ReviewService, val userService: UserService,
val categoryRepository: CategoryRepository,val imageService: ImageService,val ingredientService: IngredientService,val recipeRepository:RecipeRepository) {
    // Get all recipes
    @GetMapping
    fun getAllRecipes() : ResponseEntity<Any> {
        return ResponseEntity.ok(recipeService.getAll())
    }
    // 2 methods for adding recipe to the system, separate method for adding the images, onSubmit on frontend will call these 2 methods together
    @PostMapping
    fun addRecipe(@RequestBody recipeDTO: RecipeDTO, request: HttpServletRequest) : ResponseEntity<Any>{
        val recipe = recipeService.save(recipeDTO, userService.getCustomerByUsername(request.remoteUser))
        request.session.setAttribute("recipe",recipe)
        return ResponseEntity.ok(recipe)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteRecipe(@PathVariable id:Long,request: HttpServletRequest):ResponseEntity<Any>
    {
        val recipe=recipeService.getRecipeById(id)
        imageService.deleteByRecipe(recipe)
        reviewService.deleteByRecipe(recipe)
        ingredientService.deleteAllByRecipe(recipe)

        return ResponseEntity.ok(recipeService.deleteRecipe(recipe))

    }
    @PostMapping("/{id}/img")
    fun addRecipeImages(@PathVariable id:Long, @RequestParam requiredFile: MultipartFile,@RequestParam(required=false) optionalFile1: MultipartFile?,@RequestParam(required = false) optionalFile2: MultipartFile?, request: HttpServletRequest){
            imageRepository.save(
                Image(
                    null, requiredFile.name, requiredFile.contentType, requiredFile.bytes,
                    recipeService.getRecipeById(id)
                )
            )
        if(optionalFile1!=null) {
            imageRepository.save(
                Image(
                    null, optionalFile1.name, optionalFile1.contentType, optionalFile1.bytes,
                    recipeService.getRecipeById(id)
                )
            )
        }
        if(optionalFile2!=null) {
            imageRepository.save(
                Image(
                    null, optionalFile2.name, optionalFile2.contentType, optionalFile2.bytes,
                    recipeService.getRecipeById(id)
                )
            )
        }
    }
    @GetMapping("/images/{id}")
    fun getImagesForRecipe(@PathVariable id:Long):ResponseEntity<Any>
    {
        val recipe=recipeService.getRecipeById(id)
        return ResponseEntity.ok(imageRepository.getAllByRecipe(recipe))
    }

    // Get details for specific recipe
    @GetMapping("/details/{id}")
    fun getDetailsForRecipe(@PathVariable id:String):ResponseEntity<Any>{
        val recipe: RecipeInfoDTO = recipeService.getDetailsForRecipe(id.toLong())
        return ResponseEntity.ok(recipe)
    }
    // Add review for recipe
    @PostMapping("/addReview/{id}")
    fun addReviewForRecipe(@PathVariable id:Long, @RequestBody reviewDTO: ReviewDTO, request: HttpServletRequest):ResponseEntity<Any>{
        val recipe = recipeService.getRecipeById(id)
        val review = reviewService.addReview(Review(null,reviewDTO.content,reviewDTO.stars,
            LocalDateTime.now() ,recipe,userService.getCustomerByUsername(
            request.remoteUser
        )))
        val reviewsByRecipe=reviewService.findAllByRecipe(recipe)
        val avg=reviewsByRecipe.stream().mapToInt{it.stars!!}.summaryStatistics().average
        val bd = BigDecimal(avg)
        recipe.avRating =bd.setScale(2, RoundingMode.FLOOR).toDouble()
        recipeRepository.save(recipe)
        return ResponseEntity.ok(review)
    }
    // Get the top-rated recipes
    @GetMapping("/topRated")
    fun getTopRatedRecipes() : ResponseEntity<Any> {
        return ResponseEntity.ok(recipeService.getTopRatedRecipes())
    }
    // Get the most viewed recipes
    @GetMapping("/mostViewed")
    fun getMostViewedRecipes() : ResponseEntity<Any> {
        return ResponseEntity.ok(recipeService.getMostViewedRecipes())
    }
    // Get the newest recipes
    @GetMapping("/newest")
    fun getNewestRecipes() : ResponseEntity<Any> {
        return ResponseEntity.ok(recipeService.getNewestRecipes())
    }
    @GetMapping("/getCategories")
    fun getCategories() : ResponseEntity<Any> {
        return ResponseEntity.ok(categoryRepository.findAll())
    }
    @GetMapping("/search")
    fun getRecipesByName(@RequestParam (required = false) name:String):ResponseEntity<Any>
    {
        return ResponseEntity.ok(recipeService.findAllByNameContainingIgnoreCase(name))
    }
    @GetMapping("/category/{category}")
    fun getRecipesByCategory(@PathVariable category: String):ResponseEntity<Any>{
        return ResponseEntity.ok(recipeService.findAllByCategoryListContains(category = categoryRepository.findByNameIgnoreCase(category)))
    }
    @GetMapping("/recipesCount")
    fun getRecipesCount():ResponseEntity<Any>{
        return ResponseEntity.ok(recipeService.getNumberOfRecipes())
    }
    @GetMapping("/categoriesCount")
    fun getCategoriesCount():ResponseEntity<Any>{
        return ResponseEntity.ok(categoryRepository.count())
    }
    @GetMapping("/filtered")
    fun getFilteredRecipes(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) inputText: String?,
        @RequestParam(required = false) difficultyLevels: String?,
        @RequestParam(required = false) prepTimes: String?
    ):ResponseEntity<Any>{
        return ResponseEntity.ok(recipeService.getFilteredRecipes(category, inputText, difficultyLevels, prepTimes))
    }
    @GetMapping("/similarRecipes/{id}")
    fun getSimilarRecipes(@PathVariable id:String):ResponseEntity<Any>{
        val similarRecipes: List<RecipePreviewDTO> = recipeService.getSimilarRecipes(id.toLong())
        return ResponseEntity.ok(similarRecipes)
    }
}