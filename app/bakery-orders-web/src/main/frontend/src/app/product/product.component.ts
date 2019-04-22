import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Observable} from 'rxjs';


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {
  private products = [];
  private productsObservable: Observable<Object>;

  constructor(private productService: ProductService) {
    this.productsObservable = this.productService.getProducts();
    this.productsObservable.subscribe(resp => {
      console.log(resp);
    });
  }

  ngOnInit() {

  }

}
