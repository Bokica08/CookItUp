package com.sorsix.cookitup.model

import lombok.Data
import javax.persistence.*

@Entity
@Data
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val categoryId: Long? = null,
        val name:String?=null,
        @ManyToMany(mappedBy = "categoryList")
        val  recipeList: MutableList<Recipe> = arrayListOf()

) {
}