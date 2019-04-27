import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {CategoryService} from "../../services/category.service";
import {ProductService} from "../../services/product.service";
import {Product} from "../../model/product";
import {Category} from "../../model/category";
import {Router} from "@angular/router";
import {FileService} from "../../services/file.service";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: 'app-create-product',
    templateUrl: './product-create.component.html',
    styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent {
    form: FormGroup;
    categoriesList;
    selectedFile: File = null;
    url = null;

    constructor(private categoryService: CategoryService,
                private productService: ProductService,
                private fb: FormBuilder,
                private router: Router,
                private fileService: FileService,
                private snackBar: MatSnackBar) {
        this.form = fb.group({
            'name': [null, Validators.required],
            'cost': [null, Validators.required],
            'tax': [null, Validators.required],
            'description': [null, Validators.required],
            'categories': [null],
            'file': [null, Validators.required]
        });

        this.categoryService.getCategories().subscribe(
            data => {
                this.categoriesList = data
            },
            err => console.error(err),
        );
    }

    ngOnInit() {

    }

    onFileSelected(event) {
        if (event.target.files && event.target.files[0]) {
            // if file is not image type
            if (this.fileService.getSuffix(event.target.files[0].type) == 'unknown') {
                this.form.get('file').setErrors({'notImage': true});
                return;
            }

            // if file is image
            this.selectedFile = <File>event.target.files[0];

            // show file image
            var reader = new FileReader();
            reader.readAsDataURL(event.target.files[0]); // read file as data url
            reader.onload = () => { // called once readAsDataURL is completed
                this.url = reader.result;
            }
        }
    }

    onSubmit() {
        let product = new Product();
        product.name = this.form.get('name').value;
        product.cost = this.form.get('cost').value;
        product.tax = this.form.get('tax').value;
        product.description = this.form.get('description').value;
        product.categories = [];

        for (let valueKey in this.form.get('categories').value) {
            let category = new Category();
            category.id = this.categoriesList[valueKey].id;
            product.categories.push(category);
        }


        this.productService.createProduct(product).subscribe(
            data => {
                this.fileService.uploadImage(this.selectedFile, 'products', data.id).subscribe(
                    () => {
                    },
                    err => console.error(err),
                    () => {
                        this.router.navigateByUrl('/product').then(
                            success => {
                                this.snackBar.open("Product created!", '', {
                                    duration: 4000,
                                });
                            }
                        );

                    }
                );
            },
            err => console.error(err)
        );

    }

    getNameErrorMessage() {
        return this.form.get('name').hasError('required') ? 'You must enter a value' :
            '';
    }

    getCostErrorMessage() {
        return this.form.get('cost').hasError('required') ? 'You must enter a value' :
            '';
    }

    getTaxErrorMessage() {
        return this.form.get('tax').hasError('required') ? 'You must enter a value' :
            '';
    }

    getDescriptionErrorMessage() {
        return this.form.get('description').hasError('required') ? 'You must enter a value' :
            '';
    }

    getFileErrorMessage() {
        return this.form.get('file').hasError('notImage') ? 'Selected file is not image' :
            this.form.get('file').hasError('required') ? 'You must enter a value' :
                '';
    }

}
