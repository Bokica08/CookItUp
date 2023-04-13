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
data class Image(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val imageId: Long? = null,
    val byteArray: ByteArray?=null,
    @ManyToOne
    @JoinColumn(name = "recipeId")
    val recipe: Recipe? = null,

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Image

        if (imageId != other.imageId) return false
        if (byteArray != null) {
            if (other.byteArray == null) return false
            if (!byteArray.contentEquals(other.byteArray)) return false
        } else if (other.byteArray != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imageId?.hashCode() ?: 0
        result = 31 * result + (byteArray?.contentHashCode() ?: 0)
        return result
    }
}