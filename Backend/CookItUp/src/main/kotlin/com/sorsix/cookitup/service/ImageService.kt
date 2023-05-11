package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Image
import com.sorsix.cookitup.model.Recipe
import org.springframework.web.multipart.MultipartFile

interface ImageService {
    fun deleteByRecipe(recipe: Recipe):Any
    fun save(file:MultipartFile,id:Long): Image
    fun getAllByRecipe(recipe: Recipe):List<Image>
    fun delete(image: Image):Any

}