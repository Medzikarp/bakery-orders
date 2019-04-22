import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent implements OnInit {
  title = 'Products';
  displayedColumns: string[] = ['name', 'cost', 'tax'];
  dataSource;

  constructor(private productService: ProductService) {
    this.productService.getProducts().subscribe(
      data => {
        this.dataSource = data
      },
      err => console.error(err),
      () => console.log(this.dataSource)
    );
  }

  getRecord(row) {
    console.log(row);
  }

  ngOnInit() {
  }

}
