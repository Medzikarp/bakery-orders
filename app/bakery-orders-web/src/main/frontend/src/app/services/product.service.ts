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

    getProduct(id: number): Observable<Product> {
        return this.httpClient.get<Product>(this.baseUrl + '/product/' + id);
    }

    deleteProduct(id: number) {
        return this.httpClient.delete(this.baseUrl + '/product/' + id);
    }

    updateProduct(product: Product): Observable<Product> {
        let headers = new HttpHeaders({
            'Content-Type': 'application/json'
        });

        let options = {
            headers: headers
        };

        return this.httpClient.put<Product>(this.baseUrl + '/product/' + product.id, product, options)
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
