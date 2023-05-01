import { Category } from './category';
import { Customer } from './customer';
import { DifficultyLevel } from './difficultyLevel';
import { Image } from './image';
import { IngInRecipe } from './ingInRecipe';
import { Ingredient } from './ingredient';
import { ReviewInfo } from './reviewInfo';

export interface Recipe {
  id: number;
  name: string;
  description: string;
  numPersons: number;
  difficultyLevel: DifficultyLevel;
  prepTime: number;
  avRating: number;
  viewCount: number;
  createdOn: string;
  customer: Customer;
  categoryList: Category[];
  ingredientList: IngInRecipe[];
  imageList: Image[];
  customerName: string;
  reviews: ReviewInfo[];
}
