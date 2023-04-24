package com.sorsix.cookitup.model

import com.fasterxml.jackson.annotation.JsonBackReference
import lombok.Data
import javax.persistence.*

@Entity
@Data
class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val categoryId: Long? = null,
        val name:String?=null,
        @ManyToMany(mappedBy = "categoryList")
        @JsonBackReference
        val recipeList: MutableList<Recipe> = arrayListOf()

)