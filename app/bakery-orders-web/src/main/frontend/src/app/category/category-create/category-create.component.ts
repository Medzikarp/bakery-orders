import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CategoryService} from "../../services/category.service";
import {ProductService} from "../../services/product.service";
import {Router} from "@angular/router";
import {Product} from "../../model/product";
import {Category} from "../../model/category";

@Component({
  selector: 'app-category-create',
  templateUrl: './category-create.component.html',
  styleUrls: ['./category-create.component.css']
})
export class CategoryCreateComponent {

  createCategory: FormGroup;

  constructor(private categoryService: CategoryService, private productService: ProductService, private fb: FormBuilder, private router: Router) {
    this.createCategory = fb.group({
      'name': [null, Validators.required],
      'description': [null]
    });
  }

  onSubmit() {
    let category = new Category();
    category.name = this.createCategory.get('name').value;
    category.description = this.createCategory.get('description').value;



    this.categoryService.createCategory(category).subscribe(
      data => {
        console.log(data);
      },
      err => console.error(err),
      () => this.router.navigateByUrl('/category')
    );

  }

  getNameErrorMessage() {
    return this.createCategory.get('name').hasError('required') ? 'You must enter a value' :
      '';
  }

}
