import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Product} from "../model/product";
import {Observable} from "rxjs";
import {Order} from "../model/order";
import {DeliveryOrderProducts} from "../model/delivery-order-products";
import {Category} from "../model/category";
import {MainService} from "./main.service";

@Injectable({
    providedIn: 'root'
})
export class OrderService extends MainService {
    constructor(private httpClient: HttpClient) {
        super();
    }

    getOrders() {
        return this.httpClient.get(this.apiUrl + '/order');
    }

    createOrder(order: Order): Observable<Order> {
        return this.httpClient.post<Order>(this.apiUrl + '/order', order, this.options);
    }

    addProductsToOrder(productsOrder: DeliveryOrderProducts) {
        return this.httpClient.post<DeliveryOrderProducts>(this.apiUrl + '/orderProduct/addMultiple', productsOrder, this.options);
    }

    getProductsByOrder(id: number) {
        return this.httpClient.get(this.apiUrl + '/orderProduct/order/' + id);
    }

    getOrder(id: number): Observable<Order> {
        return this.httpClient.get<Order>(this.apiUrl + '/order/' + id);
    }

    updateOrder(order: Order): Observable<Order> {
        return this.httpClient.put<Order>(this.apiUrl + '/order/' + order.id, order, this.options);
    }

    deleteOrder(id: number) {
        return this.httpClient.delete(this.apiUrl + '/order/' + id);
    }

}
