import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CategoryService} from "../../services/category.service";
import {ProductService} from "../../services/product.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../../model/product";
import {Category} from "../../model/category";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: 'app-category-create',
    templateUrl: './category-create.component.html',
    styleUrls: ['./category-create.component.css']
})
export class CategoryCreateComponent {

    form: FormGroup;
    category: Category = new Category();

    constructor(private categoryService: CategoryService,
                private productService: ProductService,
                private fb: FormBuilder,
                private router: Router,
                private activatedRoute: ActivatedRoute,
                private snackBar: MatSnackBar
    ) {
        if (this.activatedRoute.snapshot.params.id) {
            this.category.id = this.activatedRoute.snapshot.params.id;
            this.fetchExistingCategory()
        }

        this.form = fb.group({
            'name': [null, Validators.required],
            'description': [null, [Validators.required, Validators.maxLength(200), Validators.minLength(5)]]
        });
    }

    onSubmit() {
        if (this.category.id == null) {
            let category = this.getCategoryFromForm();
            this.categoryService.createCategory(category).subscribe(
                () => {
                    this.navigateUrlAndOpenSnack('/category', 'Category created!');
                },
                err => console.error(err)
            );
        } else {
            if (this.form.dirty) {
                let category = this.getCategoryFromForm();
                this.updateCategory(category);
            } else {
                this.navigateUrlAndOpenSnack('/category', 'Category do not changed!');
            }
        }
    }

    getNameErrorMessage() {
        return this.form.get('name').hasError('required') ? 'You must enter a value' :
            '';
    }

    getDescriptionErrorMessage() {
        return this.form.get('description').hasError('required') ? 'You must enter a value' :
            this.form.get('description').hasError('maxlength') ? 'Size of description must be between 5 and 200 characters' :
                this.form.get('description').hasError('minlength') ? 'Size of description must be between 5 and 200 characters' :
                    '';
    }

    private fetchExistingCategory() {
        this.categoryService.getCategory(this.category.id).subscribe(
            category => {
                this.category = category;
                this.form.setValue({
                    'name': category.name,
                    'description': category.description
                });
            }
        );
    }

    private updateCategory(category: Category) {
        this.categoryService.updateCategory(category).subscribe(
            () => {
                this.navigateUrlAndOpenSnack('/category', 'Category updated!');
            }
        );
    }

    private getCategoryFromForm(): Category {
        let category = new Category();
        category.id = this.category.id;
        category.name = this.form.get('name').value;
        category.description = this.form.get('description').value;
        return category;
    }

    private navigateUrlAndOpenSnack(url: string, message: string) {
        this.router.navigateByUrl(url).then(
            () => {
                this.snackBar.open(message, '', {
                    duration: 4000,
                });
            }
        );
    }

}
