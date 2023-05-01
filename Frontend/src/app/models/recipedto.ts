import { Category } from './category';
import { DifficultyLevel } from './difficultyLevel';
import { IngInRecipe } from './ingInRecipe';
import { Ingredient } from './ingredient';

export class RecipeDTO {
  name!: string;
  description!: string;
  numPersons!: number;
  difficultyLevel!: DifficultyLevel;
  prepTime!: number;
  categoryList!: Category[];
  ingredientList!: IngInRecipe[];
}
