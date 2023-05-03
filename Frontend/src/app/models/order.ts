import { Recipe } from './recipe';

export interface Order {
  orderId: number;
  orderStatus: string;
  numPersons: number;
  recipe: Recipe;
}
