package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.service.IngredientService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ingredient")
class IngredientController(val ingredientService: IngredientService) {
    @GetMapping("/all")
    fun findAll():ResponseEntity<List<Ingredient>>
    {
        return ResponseEntity.ok(ingredientService.getAllIngredients())
    }
}