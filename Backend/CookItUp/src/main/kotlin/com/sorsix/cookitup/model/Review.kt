package com.sorsix.cookitup.model

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val reviewId: Long? = null,
        val content: String?=null,
        val stars:Int?=null
) {
}