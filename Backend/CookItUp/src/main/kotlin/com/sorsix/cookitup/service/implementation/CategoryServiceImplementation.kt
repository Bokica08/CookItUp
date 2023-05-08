package com.sorsix.cookitup.service.implementation

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.dto.CategoryDTO
import com.sorsix.cookitup.repository.CategoryRepository
import com.sorsix.cookitup.service.CategoryService
import org.springframework.stereotype.Service

@Service
class CategoryServiceImplementation(val categoryRepository: CategoryRepository):CategoryService {
    override fun getAllCategories(): List<Category> {
        return categoryRepository.findAll()
    }

    override fun save(category: CategoryDTO): Category {
       return categoryRepository.save(Category(null,category.name))
    }

    override fun findAll(): List<Category> {
        return categoryRepository.findAll()
    }

    override fun count(): Long {
        return categoryRepository.count()
    }

    override fun findByNameIgnoreCase(category: String): Category {
        return categoryRepository.findByNameIgnoreCase(category)
    }
}