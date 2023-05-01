import { Recipe } from './recipe';

export class OrderDto {
  phoneNumber!: string;
  address!: string;
  numPersons!: number;
  recipeId!: number;
}
