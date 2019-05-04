import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material";
import {OrderService} from "../../services/order.service";
import {Order} from "../../model/order";
import {User} from "../../model/user";

@Component({
    selector: 'app-order-create',
    templateUrl: './order-create.component.html',
    styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {

    form: FormGroup;

    constructor(private productService: ProductService,
                private orderService: OrderService,
                private fb: FormBuilder,
                private router: Router,
                private activatedRoute: ActivatedRoute,
                private snackBar: MatSnackBar) {
        this.form = fb.group({
            'name': [null, Validators.required],
        });
    }

    ngOnInit() {
    }

    onSubmit() {
        let order = this.getOrderFromForm();
        this.orderService.createProduct(order).subscribe(
            data => {
                this.navigateUrlAndOpenSnack('/order', 'Order created!');
            }
        );
    }

    private getOrderFromForm(): Order {
        let order = new Order();
        order.name = this.form.get('name').value;
        order.user = new User();
        order.user.id = 1;
        return order;
    }

    private navigateUrlAndOpenSnack(url: string, message: string) {
        this.router.navigateByUrl(url).then(
            () => {
                this.snackBar.open(message, '', {
                    duration: 4000,
                });
            }
        );
    }

    getNameErrorMessage() {
        return this.form.get('name').hasError('required') ? 'You must enter a value' :
            '';
    }

}
