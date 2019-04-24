import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../model/category";
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})

export class CategoryService {
  baseUrl: string = "http://localhost:8080/api";

  constructor(private httpClient: HttpClient) {
  }

  getCategories() {
    return this.httpClient.get(this.baseUrl + '/category');
  }

  deleteCategory(id: number) {
    return this.httpClient.delete(this.baseUrl + '/category/' + id);
  }

  createCategory(category: Category) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    let options = {
      headers: headers
    }

    return this.httpClient.post(this.baseUrl + '/category', category, options);
  }

}
