import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Product} from "../model/product";
import {Observable} from "rxjs";
import {Order} from "../model/order";
import {DeliveryOrderProducts} from "../model/delivery-order-products";

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

    createOrder(order: Order): Observable<Order> {
        return this.httpClient.post<Order>(this.baseUrl + '/order', order, this.options);
    }

    addProductsToOrder(productsOrder: DeliveryOrderProducts) {
        console.log(productsOrder);
        return this.httpClient.post<DeliveryOrderProducts>(this.baseUrl + '/orderProduct/addMultiple', productsOrder, this.options);
    }


}
