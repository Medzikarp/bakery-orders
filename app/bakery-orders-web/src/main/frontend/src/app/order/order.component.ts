import {Component, OnInit} from '@angular/core';
import {OrderService} from "../services/order.service";


@Component({
    selector: 'app-order',
    templateUrl: './order.component.html',
    styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

    displayedColumns: string[] = ['name', 'userName', 'dateTime', 'state', 'actions'];
    dataSource;
    dateFormat = {weekday: 'long', year: 'numeric', month: 'long', day: 'numeric'};

    constructor(private orderService: OrderService) {
        this.orderService.getOrders().subscribe(
            orders => {
                this.dataSource = orders;
                this.processAttributes();
            },
            error => console.log(error)
        );
    }

    ngOnInit() {
    }

    private processAttributes() {
        this.dataSource.forEach((order) => {
            order.userName = order.user.name;
            let date = new Date(Date.UTC(order.updatedAt.year, order.updatedAt.monthValue, order.updatedAt.dayOfMonth, 0, 0, 0));
            order.dateTime = date.toLocaleDateString('en-EN', this.dateFormat);
        })
    }

    onClickDelete(id: number) {
        this.orderService.deleteOrder(id).subscribe(() => this.dataSource = this.dataSource.filter(item => item.id != id));
    }


}
