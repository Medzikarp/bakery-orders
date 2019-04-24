import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../model/category";

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
}
