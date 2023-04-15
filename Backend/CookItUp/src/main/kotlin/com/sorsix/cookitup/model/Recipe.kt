package com.sorsix.cookitup.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import lombok.Data
import java.time.LocalDateTime
import javax.persistence.*

@Data
@Entity
class Recipe(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val recipeId: Long? = null,
        val name:String?=null,
        val description:String?=null,
        val numPersons:Int?=null,
        @Enumerated(value = EnumType.STRING)
        val difficultyLevel: DifficultyLevel?=null,
        val prepTime:Int?=null,
        val avRating:Int?=null,
        val viewCount:Int?=null,
        val createdOn:LocalDateTime?=null,
        @ManyToOne
        @JoinColumn(name = "userId")
        val customer: Customer?=null,
        @ManyToMany
        @JsonManagedReference
        @JoinTable(name = "of_category",
                joinColumns = [JoinColumn(name = "recipeId")],
                inverseJoinColumns = [JoinColumn(name = "categoryId")],
        )
        val categoryList: MutableList<Category> = arrayListOf())
