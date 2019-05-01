import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";

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
}
