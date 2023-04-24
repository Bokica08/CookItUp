import { Category } from "./category";
import { Customer } from "./customer";
import { DifficultyLevel } from "./difficultyLevel";
import { Image } from "./image";
import { Ingredient } from "./ingredient";

export class Recipe {
    id!: number;
    name!: string;
    description!: string;
    numPersons!: number;
    difficultyLevel!: DifficultyLevel;
    prepTime!: number;
    avRating!: number;
    viewCount!: number;
    createdOn!: string;
    customer!: Customer;
    categoryList!: Category[];
    ingredientList!: Ingredient[];
    imageList!: Image[];
}