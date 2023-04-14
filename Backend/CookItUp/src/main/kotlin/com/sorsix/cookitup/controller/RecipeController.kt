package com.sorsix.cookitup.controller

import com.sorsix.cookitup.service.RecipeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/recipe")
class RecipeController(val recipeService: RecipeService) {
    @GetMapping
    fun getAllRecipes() : ResponseEntity<Any> {
        return ResponseEntity.ok(recipeService.getAll())
    }
}