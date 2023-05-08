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

class RecipeController(val recipeService: RecipeService,
                       val reviewService: ReviewService,
                       val userService: UserService,
                       val categoryService: CategoryService,
                       val imageService: ImageService,
                       val ingredientService: IngredientService,
                       val orderService: OrderService) {
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
        orderService.deleteByRecipe(recipe)
        imageService.deleteByRecipe(recipe)
        reviewService.deleteByRecipe(recipe)
        ingredientService.deleteAllByRecipe(recipe)

        return ResponseEntity.ok(recipeService.deleteRecipe(recipe))

    }
    @PostMapping("/{id}/img")
    fun addRecipeImages(@PathVariable id:Long, @RequestParam requiredFile: MultipartFile,@RequestParam(required=false) optionalFile1: MultipartFile?,@RequestParam(required = false) optionalFile2: MultipartFile?, request: HttpServletRequest){
        imageService.save(requiredFile,id)
        if(optionalFile1!=null) {
           imageService.save(optionalFile1,id)
        }
        if(optionalFile2!=null) {
            imageService.save(optionalFile2,id)
        }
    }
    @GetMapping("/images/{id}")
    fun getImagesForRecipe(@PathVariable id:Long):ResponseEntity<Any>
    {
        val recipe=recipeService.getRecipeById(id)
        return ResponseEntity.ok(imageService.getAllByRecipe(recipe))
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
        recipeService.save(recipe)
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
        return ResponseEntity.ok(categoryService.findAll())
    }
    @GetMapping("/search")
    fun getRecipesByName(@RequestParam (required = false) name:String):ResponseEntity<Any>
    {
        return ResponseEntity.ok(recipeService.findAllByNameContainingIgnoreCase(name))
    }
    @GetMapping("/userRecipes/{username}")
    fun getAllRecipesByUser(@PathVariable username: String):ResponseEntity<Any>{
        return ResponseEntity.ok(recipeService.findAllByCustomer(userService.getCustomerByUsername(username)))
    }
    @GetMapping("/category/{category}")
    fun getRecipesByCategory(@PathVariable category: String):ResponseEntity<Any>{
        return ResponseEntity.ok(recipeService.findAllByCategoryListContains(category = categoryService.findByNameIgnoreCase(category)))
    }
    @GetMapping("/recipesCount")
    fun getRecipesCount():ResponseEntity<Any>{
        return ResponseEntity.ok(recipeService.getNumberOfRecipes())
    }
    @GetMapping("/categoriesCount")
    fun getCategoriesCount():ResponseEntity<Any>{
        return ResponseEntity.ok(categoryService.count())
    }
    @GetMapping("/filtered")
    fun getFilteredRecipes(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) inputText: String?,
        @RequestParam(required = false) difficultyLevels: String?,
        @RequestParam(required = false) prepTimes: String?,
        @RequestParam(required = false) username: String?
    ):ResponseEntity<Any>{
        return ResponseEntity.ok(recipeService.getFilteredRecipes(category, inputText, difficultyLevels, prepTimes, username))
    }
    @GetMapping("/similarRecipes/{id}")
    fun getSimilarRecipes(@PathVariable id:String):ResponseEntity<Any>{
        val similarRecipes: List<RecipePreviewDTO> = recipeService.getSimilarRecipes(id.toLong())
        return ResponseEntity.ok(similarRecipes)
    }
}