package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.Image
import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import java.time.LocalDate

data class RecipePreviewDTO(
    val id:Long?=null,
    val name:String?=null,
    val numPersons:Int?=null,
    val difficultyLevel: DifficultyLevel?=null,
    val prepTime:Int?=null,
    val avRating:Double?=null,
    val viewCount:Int?=null,
    val createdOn: LocalDate?=null,
    val customerName: String?=null,
    val imageList: List<Image>
)
