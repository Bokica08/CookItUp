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
    var name: String? = null,
    var description: String? = null,
    var numPersons: Int? = null,
    @Enumerated(value = EnumType.STRING)
    var difficultyLevel: DifficultyLevel? = null,
    var prepTime: Int? = null,
    var avRating: Double? = null,
    var viewCount: Int? = null,
    val createdOn: LocalDateTime? = null,
    @ManyToOne
    @JoinColumn(name = "userId")
    val customer: Customer? = null,
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
        name = "of_category",
        joinColumns = [JoinColumn(name = "recipeId")],
        inverseJoinColumns = [JoinColumn(name = "categoryId")],
    )
    var categoryList: MutableList<Category> = arrayListOf(),
    @ManyToMany
    @JsonManagedReference
    @JoinTable(
        name = "favorites",
        joinColumns = [JoinColumn(name = "recipeId")],
        inverseJoinColumns = [JoinColumn(name = "userId")],
    )
    val favoriteList: MutableList<Customer> = arrayListOf()
)
