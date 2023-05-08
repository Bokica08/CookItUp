package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Image
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.repository.ImageRepository
import com.sorsix.cookitup.repository.RecipeRepository
import com.sorsix.cookitup.service.ImageService
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageServiceImplemetation(val imageRepository: ImageRepository,
    val recipeRepository: RecipeRepository):ImageService {
    override fun deleteByRecipe(recipe: Recipe): Any {
        val images=imageRepository.getAllByRecipe(recipe)
        return imageRepository.deleteAll(images)
    }

    override fun save(file: MultipartFile,id:Long): Image {
        return imageRepository.save(
            Image(
                null, file.name, file.contentType, file.bytes,
                recipeRepository.getReferenceById(id)
            )
        )
    }

    override fun getAllByRecipe(recipe: Recipe): List<Image> {
      return imageRepository.getAllByRecipe(recipe);
    }
}