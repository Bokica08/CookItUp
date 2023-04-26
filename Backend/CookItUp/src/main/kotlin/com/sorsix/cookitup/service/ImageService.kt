package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Recipe

interface ImageService {
    fun deleteByRecipe(recipe: Recipe):Any
}