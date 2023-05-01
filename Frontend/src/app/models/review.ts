import { Recipe } from './recipe';
import { RecipeDTO } from './recipedto';

export interface Review {
  content: string;
  stars: number;
  reviewedOn: Date;
  recipe: Recipe;
}
