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
import {KeycloakService} from "keycloak-angular";

@Component({
    selector: 'app-order-create',
    templateUrl: './order-create.component.html',
    styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {

    form: FormGroup;
    productList;
    productOrders: ProductOrder[] = [];
    order: Order = new Order();

    constructor(private productService: ProductService,
                private orderService: OrderService,
                private fb: FormBuilder,
                private router: Router,
                private activatedRoute: ActivatedRoute,
                private snackBar: MatSnackBar,
                private keycloak: KeycloakService) {


        if (this.activatedRoute.snapshot.params.id) {
            this.order.id = this.activatedRoute.snapshot.params.id;
        }

        this.createForm();
        this.fetchData();
    }

    ngOnInit() {
    }

    private createForm() {
        this.form = this.fb.group({
            'name': [null, Validators.required],
            'total': [{value: null, disabled: true}],
            'state': [null]
        });
    }

    onSubmit() {
        let order = this.getOrderFromForm();
        console.log(order);
        if (this.order.id != null && !this.isOrderCopied()) { // update order
            order.id = this.order.id;
            this.orderService.updateOrder(order).subscribe(order => this.addProductToOrder(order, 'Order updated!'));
        } else { // create new order
            this.orderService.createOrder(order).subscribe(order => this.addProductToOrder(order, 'Order created!'));
        }

    }

    private getOrderFromForm(): Order {
        let order = new Order();
        order.name = this.form.get('name').value;
        order.state = this.form.get('state').value;
        order.user = new User();
        order.user.id = 1;

        this.productOrders.forEach(productOrder => {
            productOrder.product.id = this.form.get('productOrder' + productOrder.inputId).value;
            productOrder.quantity = this.form.get('quantityOrder' + productOrder.inputId).value;
        });
        return order;
    }

    private getDeliveryProductsFromForm(order: Order) {
        let deliveryProducts = new DeliveryOrderProducts();
        this.productOrders.forEach(productOrder => {
            productOrder.deliveryOrder = new Order();
            productOrder.deliveryOrder.id = order.id;
        });
        deliveryProducts.deliveryOrderProducts = this.productOrders;
        return deliveryProducts;
    }

    private addProductToOrder(order: Order, message: string) {
        let deliveryProducts = this.getDeliveryProductsFromForm(order);
        this.orderService.addProductsToOrder(deliveryProducts).subscribe(
            () => this.navigateUrlAndOpenSnack('/order', message)
        );
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

    private fetchData() {
        this.productService.getProducts().subscribe(
            products => {
                this.productList = products;
                if (this.order.id != null) {
                    this.fetchProductsOfOrder();
                }
            }
        )
    }

    addProduct(productOrder: ProductOrder = new ProductOrder()) {
        if (productOrder.product != null) {
            this.form.addControl('productOrder' + productOrder.inputId, new FormControl(productOrder.product.id, Validators.required));
            this.form.addControl('quantityOrder' + productOrder.inputId, new FormControl(productOrder.quantity, [Validators.required, Validators.min(1)]));
        } else {
            productOrder.inputId = this.productOrders.length.toString();
            productOrder.deliveryOrder = new Order();
            productOrder.product = new Product();
            this.form.addControl('productOrder' + productOrder.inputId, new FormControl(null, Validators.required));
            this.form.addControl('quantityOrder' + productOrder.inputId, new FormControl(1, [Validators.required, Validators.min(1)]));
            this.productOrders.push(productOrder);
        }
    }

    deleteProduct() {
        let productOrderToRemove = this.productOrders.pop();
        this.form.removeControl('productOrder' + productOrderToRemove.inputId);
        this.form.removeControl('quantityOrder' + productOrderToRemove.inputId);
        this.onTotalCostChange();
    }

    private fetchExistingOrder() {
        this.orderService.getOrder(this.order.id).subscribe(
            data => {
                this.order = data;
                this.form.get('name').setValue(data.name);
                this.form.get('state').setValue(this.order.state);
                if (!this.isAdmin()) {
                    this.form.get('state').disable();
                    if (this.order.state != 'UNCONFIRMED') {
                        this.form.disable();
                    }
                }
            }
        );
    }

    private fetchProductsOfOrder() {
        this.orderService.getProductsByOrder(this.order.id).subscribe(
            data => {
                for (let dataKey in data) {
                    let productOrder = new ProductOrder();
                    productOrder.quantity = data[dataKey].quantity;
                    productOrder.product = data[dataKey].product;
                    productOrder.inputId = 'Update' + data[dataKey].id;
                    this.addProduct(productOrder);
                    this.productOrders.push(productOrder);
                }
                this.form.get('total').setValue(this.computeOrderTotalCost());
                this.fetchExistingOrder();
            }
        );
    }

    private computeProductTotalCost(cost, tax) {
        return Math.round(((cost / 100) * tax) + cost);
    }

    private computeOrderTotalCost() {
        let totalCost: number = 0;
        this.productOrders.forEach((productOrder) => {
            let product = this.productList.find(product => product.id === this.form.get('productOrder' + productOrder.inputId).value);
            if (product != undefined) {
                totalCost += this.computeProductTotalCost(product.cost, product.tax) * this.form.get('quantityOrder' + productOrder.inputId).value;
            }
        });
        return totalCost.toFixed(2);
    }

    checkValid() {
        if (this.productOrders.length <= 0) {
            return true;
        } else {
            return !this.form.valid;
        }
    }

    onTotalCostChange() {
        this.form.get('total').setValue(this.computeOrderTotalCost());
    }

    isOrderCopied(): boolean {
        return this.activatedRoute.snapshot.url[1] != undefined && this.activatedRoute.snapshot.url[1].path == 'copy';
    }

    isAdmin() {
        return this.keycloak.isUserInRole('ADMIN');
    }


}
