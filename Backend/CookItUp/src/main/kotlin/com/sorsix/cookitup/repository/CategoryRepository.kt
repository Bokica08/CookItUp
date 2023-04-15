package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : JpaRepository<Category, Long>{
    fun findByName(name: String): Category
}