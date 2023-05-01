import { Recipe } from "./recipe";
import { OrderStatus } from "./statusEnum";

export class orderAdmin{
    orderId!:number;
    phoneNumber!:string;
    address!:string;
    numPersons!:number;
    recipe!:Recipe;
    orderStatus!:OrderStatus;
}