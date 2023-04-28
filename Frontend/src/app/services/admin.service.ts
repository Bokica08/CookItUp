import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core"
import { Observable } from "rxjs"
import { Customer } from "../models/customer"
import { Category } from "../models/category"

@Injectable({
    providedIn: 'root'
})
export class AdminService {
    adminUrl = "http://localhost:8080/api/admin"
    constructor(private http: HttpClient) { }
    viewPending(): Observable<Customer[]> {
        return this.http.get<Customer[]>(this.adminUrl + '/pending')
    }
    approveAdmin(username:string):Observable<Customer>
    {
        return this.http.get<Customer>(this.adminUrl+'/pending/authorizeAdmin?username='+username)
    }
    addCategory(category:Category):Observable<Category>
    {
        return this.http.post<Category>(this.adminUrl+"/category",category)
    }

}