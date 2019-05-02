import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Product} from "../model/product";
import {Observable} from "rxjs";
import {Order} from "../model/order";

@Injectable({
    providedIn: 'root'
})
export class OrderService {
    baseUrl: string = "http://localhost:8080/api";
    options = {
        headers: new HttpHeaders({
            'Content-Type': 'application/json'
        })
    };

    constructor(private httpClient: HttpClient) {
    }

    getOrders() {
        return this.httpClient.get(this.baseUrl + '/order');
    }

    createProduct(order: Order): Observable<Order> {
        return this.httpClient.post<Order>(this.baseUrl + '/order', order, this.options)
    }
}
