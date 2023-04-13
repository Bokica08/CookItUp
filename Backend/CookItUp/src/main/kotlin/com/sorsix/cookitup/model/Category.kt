package com.sorsix.cookitup.model

import lombok.Data
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
@Data
data class Category(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val categoryId: Long? = null,
        val name:String?=null
) {
}