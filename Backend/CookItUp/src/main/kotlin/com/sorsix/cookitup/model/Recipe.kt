package com.sorsix.cookitup.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class Recipe(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val recipeId: Long? = null,
        val name:String?=null,
        val description:String?=null,
        val numPersons:Int?=null,
        val difficultyLevel: DifficultyLevel?=null,
        val prepTime:Int?=null
        ){}
