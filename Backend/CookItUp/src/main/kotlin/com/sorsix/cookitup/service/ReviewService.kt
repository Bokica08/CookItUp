package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.Review
import com.sorsix.cookitup.model.dto.ReviewDTO
import com.sorsix.cookitup.model.dto.ReviewForRecipeDTO

interface ReviewService {
    fun findAllByCustomer(customer: Customer):List<Review>
    fun findAllByRecipe(recipe: Recipe):List<Review>
    fun addReview(review: Review): Review
    fun getReviewInfo(id:Long) : ReviewForRecipeDTO
}