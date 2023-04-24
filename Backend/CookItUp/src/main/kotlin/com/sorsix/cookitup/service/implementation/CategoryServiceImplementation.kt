package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.repository.CategoryRepository
import com.sorsix.cookitup.service.CategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImplementation(val categoryRepository: CategoryRepository):CategoryService {
    override fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }
}