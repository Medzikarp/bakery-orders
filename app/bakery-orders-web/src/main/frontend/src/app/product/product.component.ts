import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})

export class ProductComponent {
  title = 'Products';
  displayedColumns: string[] = ['name', 'cost', 'tax', 'description', 'actions'];
  dataSource;

  constructor(private productService: ProductService) {
    this.productService.getProducts().subscribe(
      data => {
        this.dataSource = data
      },
      err => console.error(err),
    );
  }

  onClickDelete(id: number) {
    this.productService.deleteProduct(id).subscribe(
      data => {

      },
      err => console.error(err),
      () => this.dataSource = this.dataSource.filter(item => item.id != id)
    );
  }
}
