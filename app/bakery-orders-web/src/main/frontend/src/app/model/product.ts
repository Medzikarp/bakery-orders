import {Category} from './category';

export class Product {
    id: number = null;
    name: String = null;
    cost: Number = null;
    tax: Number = null;
    description: String = null;
    categories: Category[] = [];
    image: String = null;
}
