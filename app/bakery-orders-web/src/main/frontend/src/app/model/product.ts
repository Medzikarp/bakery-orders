import {Category} from './category';

export class Product {
  id: Number;
  name: String;
  cost: Number;
  tax: Number;
  description: String;
  categories: Category[];
  imageUrl: String;
}
