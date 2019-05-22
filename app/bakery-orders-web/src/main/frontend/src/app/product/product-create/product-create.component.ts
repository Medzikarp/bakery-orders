import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {CategoryService} from "../../services/category.service";
import {ProductService} from "../../services/product.service";
import {Product} from "../../model/product";
import {Category} from "../../model/category";
import {ActivatedRoute, Router} from "@angular/router";
import {FileService} from "../../services/file.service";
import {MatSnackBar} from "@angular/material";

@Component({
    selector: 'app-create-product',
    templateUrl: './product-create.component.html',
    styleUrls: ['./product-create.component.css']
})
export class ProductCreateComponent {
    form;
    categoriesList;
    selectedFile: File = null;
    tmpImg = null;
    product: Product = new Product();

    constructor(private categoryService: CategoryService,
                private productService: ProductService,
                private fb: FormBuilder,
                private router: Router,
                private fileService: FileService,
                private snackBar: MatSnackBar,
                private activatedRoute: ActivatedRoute
    ) {

        this.fetchCategories();
        this.createForm(fb);

        if (this.activatedRoute.snapshot.params.id) {
            this.product.id = this.activatedRoute.snapshot.params.id;
            this.fetchExistingProduct();
        }
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
                this.tmpImg = reader.result;
            }
        }
    }

    onSubmit() {
        if (this.product.id == null) {
            let product = this.getProductFromForm();
            this.createProduct(product);
        } else {
            if (this.form.dirty) {
                let product = this.getProductFromForm();
                this.updateProduct(product);
            } else {
                this.router.navigateByUrl('/product').then(success => {
                    this.snackBar.open("Product do not changed!", '', {
                        duration: 4000,
                    });
                });
            }
        }

    }

    private fetchCategories() {
        this.categoryService.getCategories().subscribe(
            categoriesList => {
                this.categoriesList = categoriesList;
            },
            err => console.error(err)
        );
    }

    private createForm(fb: FormBuilder) {
        this.form = fb.group({
            'name': [null, Validators.required],
            'cost': [null, [Validators.required, Validators.min(0)]],
            'tax': [null, [Validators.required, Validators.min(0)]],
            'description': [null, [Validators.required, Validators.maxLength(200), Validators.minLength(5)]],
            'categories': [],
            'file': [null],
            'total': [null]
        });
        this.form.get('total').disable();
    }

    private fetchExistingProduct() {
        this.productService.getProduct(this.product.id).subscribe(
            product => {
                this.product = product;
                this.form.setValue({
                    'name': product.name,
                    'cost': product.cost,
                    'tax': product.tax,
                    'description': product.description,
                    'categories': product.categories,
                    'file': null,
                    'total': this.computeTotalCost(product.cost, product.tax)
                });
                this.tmpImg = 'http://localhost:8080/images/products/' + product.id + product.image;
            }
        );
    }

    private getProductFromForm(): Product {
        let product = new Product();
        product.name = this.form.get('name').value;
        product.cost = this.form.get('cost').value;
        product.tax = this.form.get('tax').value;
        product.description = this.form.get('description').value;
        product.categories = [];
        if (this.selectedFile != null) {
            product.image = this.fileService.getSuffix(this.selectedFile.type);
        } else {
            product.image = this.product.image;
        }
        product.id = this.product.id;

        for (let valueKey in this.form.get('categories').value) {
            let category = new Category();
            category.id = this.categoriesList[valueKey].id;
            product.categories.push(category);
        }
        return product;
    }

    private createProduct(product: Product) {

        this.productService.createProduct(product).subscribe(
            (created) => {
                this.fileService.uploadImage(this.selectedFile, 'products', created.id).subscribe(
                    () => this.navigateUrlAndOpenSnack('/product', 'Product created!'),
                    err => console.error(err),
                );
            },
            err => console.error(err)
        );
    }

    private updateProduct(product: Product) {
        this.productService.updateProduct(product).subscribe(
            (created) => {
                if (this.selectedFile == null) {
                    this.navigateUrlAndOpenSnack('/product', 'Product updated!');
                } else {
                    this.fileService.uploadImage(this.selectedFile, 'products', created.id).subscribe(
                        () => this.navigateUrlAndOpenSnack('/product', 'Product updated!'),
                        err => console.error(err)
                    );
                }

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
            this.form.get('cost').hasError('min') ? 'Cost must be positive' :
                '';
    }

    getTaxErrorMessage() {
        return this.form.get('tax').hasError('required') ? 'You must enter a value' :
            this.form.get('tax').hasError('min') ? 'Tax must be positive' :
                '';
    }

    getDescriptionErrorMessage() {
        return this.form.get('description').hasError('required') ? 'You must enter a value' :
            this.form.get('description').hasError('maxlength') ? 'Size of description must be between 5 and 200 characters' :
                this.form.get('description').hasError('minlength') ? 'Size of description must be between 5 and 200 characters' :
                    '';
    }

    getFileErrorMessage() {
        return this.form.get('file').hasError('notImage') ? 'Selected file is not image' :
            '';
    }

    compareCategories(o1: Category, o2: Category) {
        if (o1 != null && o2 != null) {
            return o1.id === o2.id;
        }
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

    private computeTotalCost(cost, tax) {
        if (cost < 0 || tax < 0) {
            return 'Incorrect input, cost and tax must be positive.';
        }
        return Math.round(((cost / 100) * tax) + cost).toFixed(2);
    }

    onTotalCostChange() {
        let newCost = this.computeTotalCost(this.form.get('cost').value, this.form.get('tax').value);
        this.form.get('total').setValue(newCost);
    }

}
