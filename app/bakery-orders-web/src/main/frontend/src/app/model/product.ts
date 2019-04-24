import {Category} from './category';

export class Product {
  id: Number;
  name: String;
  cost: Number;
  tax: Number;
  categories: Category[];
}
