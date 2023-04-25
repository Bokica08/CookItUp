import { Recipe } from "./recipe";

export interface Review {
    content:string,
    stars:number,
    reviewedOn:Date,
    recipe:Recipe
}