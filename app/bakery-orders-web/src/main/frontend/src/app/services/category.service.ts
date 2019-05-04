import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Category} from "../model/category";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class CategoryService {
    baseUrl: string = "http://localhost:8080/api";
    options = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private httpClient: HttpClient) {
    }

    getCategory(id: number): Observable<Category> {
        return this.httpClient.get<Category>(this.baseUrl + '/category/' + id);
    }

    getCategories() {
        return this.httpClient.get(this.baseUrl + '/category');
    }

    deleteCategory(id: number) {
        return this.httpClient.delete(this.baseUrl + '/category/' + id);
    }

    createCategory(category: Category): Observable<Category> {
        return this.httpClient.post<Category>(this.baseUrl + '/category', category, this.options);
    }

    updateCategory(category: Category): Observable<Category> {
        return this.httpClient.put<Category>(this.baseUrl + '/category/' + category.id, category, this.options);
    }


}
