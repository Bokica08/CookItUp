package com.sorsix.cookitup.model.manyToMany

import lombok.Data
import org.hibernate.Hibernate
import java.io.Serializable
import java.util.*

@Data
class IngredientIsInRecipeId(
    val ingredientId:Long,
    val recipeId:Long):Serializable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as IngredientIsInRecipeId

        return ingredientId == other.ingredientId
                && recipeId == other.recipeId
    }

    override fun hashCode(): Int = Objects.hash(ingredientId, recipeId)

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(ingredientId = $ingredientId , recipeId = $recipeId )"
    }
}