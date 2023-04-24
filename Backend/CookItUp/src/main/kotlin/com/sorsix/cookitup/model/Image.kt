package com.sorsix.cookitup.model

import lombok.Data
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity
@Data
class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val imageId: Long? = null,
    val name: String? = null,
    val type: String? = null,
    val byteArray: ByteArray?=null,
    @ManyToOne
    @JoinColumn(name = "recipeId")
    val recipe: Recipe? = null,

)