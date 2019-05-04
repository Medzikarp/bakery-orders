import {Product} from "./product";
import {Order} from "./order";

export class ProductOrder {
    inputId: number;
    product: Product;
    deliveryOrder: Order;
    quantity: number;
}
