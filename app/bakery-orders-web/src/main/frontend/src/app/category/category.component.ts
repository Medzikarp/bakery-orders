import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../services/category.service";

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {

  title = 'Categories';
  displayedColumns: string[] = ['name', 'description'];
  dataSource;

  constructor(private categoryService: CategoryService) {
    this.categoryService.getCategories().subscribe(
      data => {
        this.dataSource = data
      },
      err => console.error(err),
    );
  }

  onClickDelete(id: number) {
    this.categoryService.deleteCategory(id).subscribe(
      data => {

      },
      err => console.error(err),
      () => this.dataSource = this.dataSource.filter(item => item.id != id)
    );
  }

}
