import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Product} from "../model/product";
import {Observable} from "rxjs";
import {MainService} from "./main.service";

@Injectable({
    providedIn: 'root'
})
export class ProductService extends MainService {
    constructor(private httpClient: HttpClient) {
        super();
    }

    getProducts() {
        return this.httpClient.get(this.apiUrl + '/product');
    }

    getProduct(id: number): Observable<Product> {
        return this.httpClient.get<Product>(this.apiUrl + '/product/' + id);
    }

    deleteProduct(id: number) {
        return this.httpClient.delete(this.apiUrl + '/product/' + id);
    }

    updateProduct(product: Product): Observable<Product> {
        return this.httpClient.put<Product>(this.apiUrl + '/product/' + product.id, product, this.options);
    }

    createProduct(product: Product): Observable<Product> {
        return this.httpClient.post<Product>(this.apiUrl + '/product', product, this.options)
    }

    getProductsByCategory(id: number) {
        return this.httpClient.get(this.apiUrl + '/product/category/' + id);
    }

}
