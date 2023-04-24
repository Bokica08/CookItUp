package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Category

interface CategoryService {
    fun getAllCategories():List<Category>
}