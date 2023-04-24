import { Category } from "./category";
import { DifficultyLevel } from "./difficultyLevel";
import { Ingredient } from "./ingredient";

export interface RecipeDTO {
    name:      string;
    description:     string;
    numPersons:   number;
    difficultyLevel:   DifficultyLevel;
    prepTime:  number;
    customerId:   number;
    categoryList:    Category[];
    ingredientList:  Ingredient[];
}