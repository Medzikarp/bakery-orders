import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Product} from "../model/product";
import {Observable} from "rxjs";
import {Order} from "../model/order";
import {DeliveryOrderProducts} from "../model/delivery-order-products";
import {Category} from "../model/category";

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
        return this.httpClient.post<DeliveryOrderProducts>(this.baseUrl + '/orderProduct/addMultiple', productsOrder, this.options);
    }

    getProductsByOrder(id: number) {
        return this.httpClient.get(this.baseUrl + '/orderProduct/order/' + id);
    }

    getOrder(id: number): Observable<Order> {
        return this.httpClient.get<Order>(this.baseUrl + '/order/' + id);
    }

    updateOrder(order: Order): Observable<Order> {
        return this.httpClient.put<Order>(this.baseUrl + '/order/' + order.id, order, this.options);
    }

}
