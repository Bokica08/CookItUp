package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.service.CategoryService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true", maxAge = 3600)
class CategoryController(val categoryService: CategoryService) {
    @GetMapping("/all")
    fun getAll():List<Category>
    {
        return categoryService.getAllCategories()
    }

}