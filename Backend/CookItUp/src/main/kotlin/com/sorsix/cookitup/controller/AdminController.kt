package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.dto.CategoryDTO
import com.sorsix.cookitup.model.dto.IngredientDTO
import com.sorsix.cookitup.repository.CategoryRepository
import com.sorsix.cookitup.repository.IngredientRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin")
class AdminController(
    val categoryRepository: CategoryRepository,
    val ingredientRepository: IngredientRepository,
) {
    @PostMapping("/category")
    fun addCategory(@RequestBody categoryDTO: CategoryDTO) : ResponseEntity<Any> {
      return ResponseEntity.ok(categoryRepository.save(Category(null,categoryDTO.name)))
    }
    @PostMapping("/ingredient")
    fun addIngredient(@RequestBody ingredientDTO: IngredientDTO) : ResponseEntity<Any> {
        return ResponseEntity.ok(ingredientRepository.save(Ingredient(null,ingredientDTO.name,ingredientDTO.description)))
    }
}