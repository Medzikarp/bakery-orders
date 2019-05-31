import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Category} from "../model/category";
import {Observable} from "rxjs";
import {MainService} from "./main.service";

@Injectable({
    providedIn: 'root'
})
export class CategoryService extends MainService {
    constructor(private httpClient: HttpClient) {
        super();
    }

    getCategory(id: number): Observable<Category> {
        return this.httpClient.get<Category>(this.apiUrl + '/category/' + id);
    }

    getCategories() {
        return this.httpClient.get(this.apiUrl + '/category');
    }

    deleteCategory(id: number) {
        return this.httpClient.delete(this.apiUrl + '/category/' + id);
    }

    createCategory(category: Category): Observable<Category> {
        return this.httpClient.post<Category>(this.apiUrl + '/category', category, this.options);
    }

    updateCategory(category: Category): Observable<Category> {
        return this.httpClient.put<Category>(this.apiUrl + '/category/' + category.id, category, this.options);
    }


}
