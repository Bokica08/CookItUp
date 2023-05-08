package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.dto.CategoryDTO

interface CategoryService {
    fun getAllCategories():List<Category>
    fun save(category: CategoryDTO):Category
    fun findAll():List<Category>
    fun count():Long
    fun findByNameIgnoreCase(category:String):Category
}