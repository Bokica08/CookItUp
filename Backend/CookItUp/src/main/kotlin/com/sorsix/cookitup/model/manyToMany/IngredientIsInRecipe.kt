package com.sorsix.cookitup.model.manyToMany

import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.enumeration.Measure
import lombok.Data
import org.hibernate.Hibernate
import java.util.*
import javax.persistence.*

@Entity
@Data
@Table(name = "ingredient_is_in_recipe")
data class IngredientIsInRecipe(
    @EmbeddedId
    val id:IngredientIsInRecipeId,
    @MapsId("ingredientId")
    @ManyToOne
    val ingredient: Ingredient,
    @MapsId("recipeId")
    @ManyToOne
    val recipe: Recipe,
    val quantity:Int,
    @Enumerated(value = EnumType.STRING)
    val measure:Measure

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as IngredientIsInRecipe

        return id == other.id
    }

    override fun hashCode(): Int = Objects.hash(id)

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(EmbeddedId = $id , ingredient = $ingredient , recipe = $recipe , quantity = $quantity , measure = $measure )"
    }
}
