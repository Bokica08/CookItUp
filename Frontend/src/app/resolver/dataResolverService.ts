import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { StorageService } from "../_services/storage.service";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { Category } from "../models/category";
import { categoryService } from "../services/category.service";

@Injectable({providedIn:'root'})
export class dataResolverLoggedIn implements Resolve<boolean>{

    constructor(private storageService:StorageService, private httpClient:HttpClient){

    }


    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        {
            ;
            return this.storageService.isLoggedIn();
        }
    }

  
}
@Injectable({providedIn:'root'})
export class dataResolverGetAdmin implements Resolve<any>{

    constructor(private storageService:StorageService){

    }


    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any | Observable<any> | Promise<any> {
        {
            return this.storageService.getUser();
        }
    }
}
@Injectable({providedIn:'root'})
export class dataResolverGetCategories implements Resolve<Category[]>{

    constructor(private httpClient:HttpClient,private categoryService:categoryService){

    }


    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): any | Observable<any> | Promise<any> {
        {
            return this.categoryService.getAllCateogries();
        }
    }
}