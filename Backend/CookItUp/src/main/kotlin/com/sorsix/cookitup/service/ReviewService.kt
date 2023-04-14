package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.Review

interface ReviewService {
    fun findAllByCustomer(customer: Customer):List<Review>
    fun findAllByRecipe(recipe: Recipe):List<Review>
}