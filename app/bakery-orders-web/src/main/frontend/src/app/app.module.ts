import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FlexLayoutModule} from '@angular/flex-layout';
import {MaterialModule} from './material-module';
import {AppComponent} from './app.component';
import {RouterModule, Routes} from '@angular/router';
import {ProductComponent} from './product/product.component';
import {HomeComponent} from './home/home.component';
import {HttpClientModule} from '@angular/common/http';
import {ProductCreateComponent} from './product/product-create/product-create.component';
import {CategoryComponent} from './category/category.component';
import {CategoryCreateComponent} from './category/category-create/category-create.component';
import {MaterialFileInputModule} from 'ngx-material-file-input';
import {OrderComponent} from './order/order.component';
import { OrderCreateComponent } from './order/order-create/order-create.component';

const appRoutes: Routes = [
    {path: '', component: HomeComponent},
    {path: 'product', component: ProductComponent},
    {path: 'product/create', component: ProductCreateComponent},
    {path: 'product/:id', component: ProductCreateComponent},
    {path: 'category', component: CategoryComponent},
    {path: 'category/create', component: CategoryCreateComponent},
    {path: 'category/:id', component: CategoryCreateComponent},
    {path: 'order/create', component: OrderCreateComponent},
    {path: 'order/copy/:id', component: OrderCreateComponent},
    {path: 'order/:id', component: OrderCreateComponent},
    {path: 'order', component: OrderComponent}

];

@NgModule({
    declarations: [
        AppComponent,
        HomeComponent,
        ProductComponent,
        ProductCreateComponent,
        CategoryComponent,
        CategoryCreateComponent,
        OrderComponent,
        OrderCreateComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MaterialModule,
        FlexLayoutModule,
        RouterModule.forRoot(
            appRoutes
        ),
        HttpClientModule,
        MaterialFileInputModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
