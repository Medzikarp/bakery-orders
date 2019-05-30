import {Component, OnInit, ViewChild} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product";
import {CategoryService} from "../services/category.service";
import {Category} from "../model/category";
import {MatPaginator, MatSnackBar} from "@angular/material";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {FormControl} from "@angular/forms";

@Component({
    selector: 'app-home',
    templateUrl: './home.component.html',
    styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
    @ViewChild(MatPaginator) paginator: MatPaginator;

    paginatorSize: number;
    numberOfProductsDisplayedInPage: number = 8;
    allProducts: Array<Product>;
    showedProducts: Array<Product>;
    categories: Array<Category>;
    filteredOptions: Observable<string[]>;
    autocomplete = new FormControl();
    categorizedProducts: Array<Product>;
    fetchingProducts: boolean = true;
    selectOrder = new FormControl();

    constructor(private productService: ProductService, private  categoryService: CategoryService, private snackBar: MatSnackBar) {
        this.fetchProducts();
        this.fetchCategories();
    }

    ngOnInit() {
    }

    onPagination(event) {
        this.showedProducts = this.allProducts.slice(event.pageIndex * this.numberOfProductsDisplayedInPage, (event.pageIndex + 1) * this.numberOfProductsDisplayedInPage);
    }

    onSelectCategory(event) {
        this.autocomplete.reset();
        this.selectOrder.reset();
        if (event.value === 'all') {
            this.productService.getProducts().subscribe(products => {
                this.setShowedProducts(products as Array<Product>);
                this.showMessage('Showed products from all categories!');
            });
        } else {
            this.productService.getProductsByCategory(event.value.id).subscribe(products => {
                this.setShowedProducts(products as Array<Product>);
                this.showMessage('Showed products from ' + event.value.name + ' category!');
            });
        }

    }

    onSelectSort(event) {
        this.autocomplete.reset();
        if (event.value == 'without') {
            this.setShowedProducts(this.categorizedProducts);
            this.showMessage('Products unordered!');
        } else if (event.value == 'asc') {
            let ascProducts = this.nonMutationSortAsc(this.categorizedProducts);
            this.setShowedProductsWithoutAffectingCategorized(ascProducts);
            this.showMessage('Products sorted in ascending order!');
        } else {
            let descProducts = this.nonMutationSortDesc(this.categorizedProducts);
            this.setShowedProductsWithoutAffectingCategorized(descProducts);
            this.showMessage('Products sorted in descending order!');
        }

    }

    private _filter(value: string): string[] {
        if (value != null) {
            const filterValue = value.toLowerCase();
            let filtered = this.categorizedProducts.filter(product => product.name.toLowerCase().indexOf(filterValue) === 0);

            if (this.selectOrder.value != null && this.selectOrder.value != '') {
                filtered = this.sortProducts(filtered, this.selectOrder.value);
            }
            this.setShowedProductsWithoutAffectingCategorized(filtered);
            return filtered.map(option => option.name.toString());
        }
    }

    private fetchProducts() {
        this.fetchingProducts = true;
        this.productService.getProducts().subscribe(products => {
            this.setShowedProducts(products as Array<Product>);
            this.fetchingProducts = false;
        });
    }

    private fetchCategories() {
        this.categoryService.getCategories().subscribe(categories => {
            this.categories = categories as Array<Category>;
        });
    }

    private setShowedProducts(products: Array<Product>,) {
        this.setShowedProductsWithoutAffectingCategorized(products);
        this.filteredOptions = this.autocomplete.valueChanges.pipe(startWith(''), map(value => this._filter(value)));
        this.categorizedProducts = this.allProducts;
    }

    private setShowedProductsWithoutAffectingCategorized(products: Array<Product>) {
        this.allProducts = products;
        this.paginatorSize = this.allProducts.length;
        this.showedProducts = this.allProducts.slice(0, this.numberOfProductsDisplayedInPage);
        if (this.paginator != undefined) {
            this.paginator.pageIndex = 0
        }
    }

    private nonMutationSortAsc(arr: Array<Product>) {
        return [].concat(arr).sort((a, b) => a.cost.valueOf() - b.cost.valueOf());
    }

    private nonMutationSortDesc(arr: Array<Product>) {
        return [].concat(arr).sort((a, b) => b.cost.valueOf() - a.cost.valueOf());
    }

    private showMessage(message: string) {
        this.snackBar.open(message, '', {
            duration: 4000,
        });
    }

    private sortProducts(products: Array<Product>, order: string): Array<Product> {
        if (order == 'without') {
            return products;
        } else if (order == 'asc') {
            return this.nonMutationSortAsc(products);
        } else {
            return this.nonMutationSortDesc(products);
        }
    }

}
