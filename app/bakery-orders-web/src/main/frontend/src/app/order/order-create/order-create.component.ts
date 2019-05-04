import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {MatSnackBar} from "@angular/material";
import {OrderService} from "../../services/order.service";
import {Order} from "../../model/order";
import {User} from "../../model/user";
import {ProductOrder} from "../../model/product-order";
import {Product} from "../../model/product";
import {DeliveryOrderProducts} from "../../model/delivery-order-products";

@Component({
    selector: 'app-order-create',
    templateUrl: './order-create.component.html',
    styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {

    form: FormGroup;
    productList;
    productOrders: ProductOrder[] = [];

    constructor(private productService: ProductService,
                private orderService: OrderService,
                private fb: FormBuilder,
                private router: Router,
                private activatedRoute: ActivatedRoute,
                private snackBar: MatSnackBar) {
        this.fetchProducts();
        this.form = fb.group({
            'name': [null, Validators.required],
        });
    }

    ngOnInit() {
    }

    onSubmit() {
        let order = this.getOrderFromForm();
        this.orderService.createOrder(order).subscribe(
            data => {
                let deliveryProducts = new DeliveryOrderProducts();
                this.productOrders.forEach(productOrder => {
                    productOrder.deliveryOrder = new Order();
                    productOrder.deliveryOrder.id = data.id;
                });
                deliveryProducts.deliveryOrderProducts = this.productOrders;
                this.orderService.addProductsToOrder(deliveryProducts).subscribe(
                    result => {
                        this.navigateUrlAndOpenSnack('/order', 'Order created!');
                    }
                );
            }
        );
    }

    private getOrderFromForm(): Order {
        let order = new Order();
        order.name = this.form.get('name').value;
        order.user = new User();
        order.user.id = 1;

        this.productOrders.forEach(productOrder => {
            productOrder.product.id = this.form.get('productOrder' + productOrder.inputId).value.id;
            productOrder.quantity = this.form.get('quantityOrder' + productOrder.inputId).value;
        });
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

    private fetchProducts() {
        this.productService.getProducts().subscribe(
            products => this.productList = products
        )
    }

    addProduct() {
        let productOrder = new ProductOrder();
        productOrder.inputId = this.productOrders.length;
        productOrder.deliveryOrder = new Order();
        productOrder.product = new Product();
        this.form.addControl('productOrder' + productOrder.inputId, new FormControl(''));
        this.form.addControl('quantityOrder' + productOrder.inputId, new FormControl(''));
        this.productOrders.push(productOrder);
    }

    deleteProduct() {
        this.productOrders.pop();
        this.form.removeControl('productOrder' + this.productOrders.length);
        this.form.removeControl('quantityOrder' + this.productOrders.length);
    }

}
