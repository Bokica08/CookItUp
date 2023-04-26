package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.repository.ImageRepository
import com.sorsix.cookitup.service.ImageService
import org.springframework.stereotype.Service

@Service
class ImageServiceImplemetation(private val imageRepository: ImageRepository):ImageService {
    override fun deleteByRecipe(recipe: Recipe): Any {
        val images=imageRepository.getAllByRecipe(recipe)
        return imageRepository.deleteAll(images)
    }
}