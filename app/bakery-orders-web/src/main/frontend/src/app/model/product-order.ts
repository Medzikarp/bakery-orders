import {Product} from "./product";
import {Order} from "./order";

export class ProductOrder {
    inputId: string;
    product: Product;
    deliveryOrder: Order;
    quantity: number;
}
