import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Product} from "../model/product";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl: string = "http://localhost:8080/api";


  constructor(private httpClient: HttpClient) {
  }

  getProducts() {
    return this.httpClient.get<Product>(this.baseUrl + '/product');
  }

  deleteProduct(id: number) {
    return this.httpClient.delete(this.baseUrl + '/product/' + id);
  }

  createProduct(product: Product): Observable<Product> {

    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });

    let options = {
      headers: headers
    };

    return this.httpClient.post<Product>(this.baseUrl + '/product', product, options)
  }

}
