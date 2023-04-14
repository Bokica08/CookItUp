package com.sorsix.cookitup.model

import lombok.Data
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.*

@Entity
@Data
class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val reviewId: Long? = null,
        val content: String?=null,
        val stars:Int?=null,
        @ManyToOne
        @JoinColumn(name = "recipeId")
        val recipe: Recipe?=null,
        @ManyToOne
        @JoinColumn(name = "userId")
        val customer: Customer?=null,

) {
}