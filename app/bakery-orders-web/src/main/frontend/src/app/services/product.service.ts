import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Product} from "../model/product";
import {Observable} from "rxjs";

@Injectable({
    providedIn: 'root'
})
export class ProductService {
    baseUrl: string = "http://localhost:8080/api";
    options = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private httpClient: HttpClient) {
    }

    getProducts() {
        return this.httpClient.get(this.baseUrl + '/product');
    }

    getProduct(id: number): Observable<Product> {
        return this.httpClient.get<Product>(this.baseUrl + '/product/' + id);
    }

    deleteProduct(id: number) {
        return this.httpClient.delete(this.baseUrl + '/product/' + id);
    }

    updateProduct(product: Product): Observable<Product> {
        return this.httpClient.put<Product>(this.baseUrl + '/product/' + product.id, product, this.options);
    }

    createProduct(product: Product): Observable<Product> {
        return this.httpClient.post<Product>(this.baseUrl + '/product', product, this.options)
    }

    getProductsByCategory(id: number) {
        return this.httpClient.get(this.baseUrl + '/product/category/' + id);
    }

}
