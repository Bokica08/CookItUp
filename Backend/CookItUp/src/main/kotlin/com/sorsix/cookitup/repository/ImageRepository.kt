package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Image
import com.sorsix.cookitup.model.Recipe
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : JpaRepository<Image, Long> {
    fun getAllByRecipe(recipe: Recipe): List<Image>
}