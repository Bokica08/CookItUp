import { HttpClient } from "@angular/common/http";
import { Category } from "../models/category";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";
@Injectable({
    providedIn: "root"
})
export class categoryService{
    categoryUlr='http://localhost:8080/api/category'
    constructor(private httpClient:HttpClient){}
    getAllCateogries(): Observable<Category[]> {
        return this.httpClient.get<Category[]>(`${this.categoryUlr}/all`);
    }
}