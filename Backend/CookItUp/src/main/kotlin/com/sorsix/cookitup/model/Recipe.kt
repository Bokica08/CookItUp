package com.sorsix.cookitup.model

import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import lombok.Data
import java.time.LocalDateTime
import javax.persistence.*

@Data
@Entity
data class Recipe(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val recipeId: Long? = null,
        val name:String?=null,
        val description:String?=null,
        val numPersons:Int?=null,
        val difficultyLevel: DifficultyLevel?=null,
        val prepTime:Int?=null,
        val avRating:Int?=null,
        val viewCount:Int?=null,
        val createdOn:LocalDateTime?=null,
        @ManyToOne
        @JoinColumn(name = "userId")
        val user: User?=null,
        ){}
