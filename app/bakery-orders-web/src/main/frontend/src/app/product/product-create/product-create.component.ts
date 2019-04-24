import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {CategoryService} from "../../services/category.service";
import {ProductService} from "../../services/product.service";
import {Product} from "../../model/product";
import {Category} from "../../model/category";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-product',
  templateUrl: './product-create.component.html',
  styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent {
  createProduct: FormGroup;
  categoriesList;

  constructor(private categoryService: CategoryService, private productService: ProductService, private fb: FormBuilder, private router: Router) {
    this.createProduct = fb.group({
      'name': [null, Validators.required],
      'cost': [null, Validators.required],
      'tax': [null, Validators.required],
      'description': [null, Validators.required],
      'categories': [null]
    });

    this.categoryService.getCategories().subscribe(
      data => {
        console.log(data);
        this.categoriesList = data
      },
      err => console.error(err),
    );
  }

  ngOnInit() {

  }

  onSubmit() {
    let product = new Product();
    product.name = this.createProduct.get('name').value;
    product.cost = this.createProduct.get('cost').value;
    product.tax = this.createProduct.get('tax').value;
    product.description = this.createProduct.get('description').value;
    product.categories = [];

    for (let valueKey in this.createProduct.get('categories').value) {
      let category = new Category();
      category.id = this.categoriesList[valueKey].id;
      product.categories.push(category);
    }

    this.productService.createProduct(product).subscribe(
      data => {
        console.log(data);
      },
      err => console.error(err),
      () => this.router.navigateByUrl('/product')
    );

  }

  getNameErrorMessage() {
    return this.createProduct.get('name').hasError('required') ? 'You must enter a value' :
      '';
  }

  getCostErrorMessage() {
    return this.createProduct.get('cost').hasError('required') ? 'You must enter a value' :
      '';
  }

  getTaxErrorMessage() {
    return this.createProduct.get('tax').hasError('required') ? 'You must enter a value' :
      '';
  }

  getDescriptionErrorMessage() {
    return this.createProduct.get('description').hasError('required') ? 'You must enter a value' :
      '';
  }

}
