package com.sorsix.cookitup.model.manyToMany

import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.enumeration.Measure
import lombok.Data
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

)
