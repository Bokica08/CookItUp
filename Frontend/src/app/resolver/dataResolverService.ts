import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { StorageService } from "../_services/storage.service";
import { Observable } from "rxjs";

@Injectable({providedIn:'root'})
export class dataResolverLoggedIn implements Resolve<boolean>{

    constructor(private storageService:StorageService){

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