import { Recipe } from './recipe';

export interface Order {
  orderStatus: string;
  numPersons: number;
  recipe: Recipe;
}
