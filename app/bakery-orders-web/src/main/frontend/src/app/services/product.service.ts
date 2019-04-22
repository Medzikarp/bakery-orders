import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

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
}
