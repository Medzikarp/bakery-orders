import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Product} from "../model/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  baseUrl: string = "http://localhost:8080/api";


  constructor(private httpClient: HttpClient) {
  }

  getProducts() {
    return this.httpClient.get(this.baseUrl + '/product');
  }

  deleteProduct(id: number) {
    return this.httpClient.delete(this.baseUrl + '/product/' + id);
  }

  createProduct(product: Product) {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    let options = {
      headers: headers
    }

    return this.httpClient.post(this.baseUrl + '/product', product, options);
  }

}
