package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RecipeRepository : JpaRepository<Recipe, Long> {
    fun findAllByCustomer(customer: Customer) : List<Recipe>
    fun findAllByDifficultyLevel(difficultyLevel: DifficultyLevel) : List<Recipe>
    fun findAllByNameContainingIgnoreCase(name: String) : List<Recipe>
    override fun findAll() : List<Recipe>
}