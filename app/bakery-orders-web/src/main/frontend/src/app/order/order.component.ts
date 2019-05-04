import {Component, OnInit} from '@angular/core';
import {OrderService} from "../services/order.service";


@Component({
    selector: 'app-order',
    templateUrl: './order.component.html',
    styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

    displayedColumns: string[] = ['name', 'userName', 'dateTime'];
    dataSource;
    dateFormat = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'};

    constructor(private orderService: OrderService) {
        this.orderService.getOrders().subscribe(
            orders => {
                console.log(orders);
                this.dataSource = orders;
                this.processUserName();
            },
            error => console.log(error)
        );
    }

    ngOnInit() {
    }

    private processUserName() {
        this.dataSource.forEach((order) => {
            order.userName = order.user.name;
            let date = new Date(Date.UTC(order.updatedAt.year, order.updatedAt.monthValue, order.updatedAt.dayOfMonth, 0, 0, 0));
            order.dateTime = date.toLocaleDateString('en-EN', this.dateFormat);
        })
    }

}
