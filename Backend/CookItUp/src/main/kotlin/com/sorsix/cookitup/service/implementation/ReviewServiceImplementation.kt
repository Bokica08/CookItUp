package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.Review
import com.sorsix.cookitup.model.dto.ReviewForRecipeDTO
import com.sorsix.cookitup.repository.ReviewRepository
import com.sorsix.cookitup.service.ReviewService
import org.springframework.stereotype.Service

@Service
class ReviewServiceImplementation(private val repository: ReviewRepository) : ReviewService {
    override fun findAllByCustomer(customer: Customer): List<Review> {
        return repository.findAllByCustomer(customer)
    }

    override fun findAllByRecipe(recipe: Recipe): List<Review> {
        return repository.findAllByRecipe(recipe)
    }

    override fun addReview(review: Review): Review {
        return repository.save(review)
    }

    override fun getReviewInfo(id: Long) : ReviewForRecipeDTO {
        return repository.findById(id).map { ReviewForRecipeDTO(it.content, it.stars!!,it.customer!!.username,it.reviewedOn!!.toLocalDate()) }.get()
    }
}