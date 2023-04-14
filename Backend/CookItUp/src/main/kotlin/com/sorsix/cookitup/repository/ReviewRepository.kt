package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReviewRepository : JpaRepository<Review, Long> {
    fun findAllByCustomer(customer: Customer) : List<Review>
    fun findAllByRecipe(recipe: Recipe) : List<Review>
}