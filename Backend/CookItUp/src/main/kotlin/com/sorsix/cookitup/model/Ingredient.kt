package com.sorsix.cookitup.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class Ingredient(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val ingredientId: Long? = null,
        val name:String?=null,
        val description:String?=null,

) {
}