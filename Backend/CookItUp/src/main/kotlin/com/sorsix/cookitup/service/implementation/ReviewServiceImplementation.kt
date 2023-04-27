package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Image
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.Review
import com.sorsix.cookitup.model.dto.RecipePreviewDTO
import com.sorsix.cookitup.model.dto.ReviewForRecipeDTO
import com.sorsix.cookitup.model.dto.ReviewPreviewDTO
import com.sorsix.cookitup.repository.ImageRepository
import com.sorsix.cookitup.repository.RecipeRepository
import com.sorsix.cookitup.repository.ReviewRepository
import com.sorsix.cookitup.service.ReviewService
import org.springframework.stereotype.Service

@Service
class ReviewServiceImplementation(private val repository: ReviewRepository,
private val imageRepository: ImageRepository, private val recipeRepository: RecipeRepository) : ReviewService {
    override fun findAllByCustomer(customer: Customer): List<ReviewPreviewDTO> {
        return repository.findAllByCustomer(customer).map {
            this.getReviewPreview(it.reviewId!!)
        }
    }

    override fun findAllByRecipe(recipe: Recipe): List<Review> {
        return repository.findAllByRecipe(recipe)
    }

    override fun addReview(review: Review): Review {
        return repository.save(review)
    }

    override fun deleteByRecipe(recipe: Recipe): Any {
        val reviews:List<Review> = repository.findAllByRecipe(recipe)
        return repository.deleteAll(reviews)
    }

    override fun getReviewInfo(id: Long) : ReviewForRecipeDTO {
        return repository.findById(id).map { ReviewForRecipeDTO(it.content, it.stars!!,it.customer!!.username,it.reviewedOn!!.toLocalDate()) }.get()
    }

    override fun getReviewPreview(id: Long): ReviewPreviewDTO {
        val review:Review = repository.findById(id).get()
        val recipe = recipeRepository.getReferenceById(review.recipe!!.recipeId!!)
        val imageList: List<Image> = imageRepository.getAllByRecipe(recipe)
        val recipePreview =  RecipePreviewDTO(
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
        return ReviewPreviewDTO(
            review.reviewId!!,
            review.content,
            review.stars!!,
            review.reviewedOn!!,
            recipePreview,
            review.customer!!
        )
    }
}