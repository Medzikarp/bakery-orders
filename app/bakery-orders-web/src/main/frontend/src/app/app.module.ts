import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';
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
import {OrderCreateComponent} from './order/order-create/order-create.component';
import {KeycloakService, KeycloakAngularModule} from "keycloak-angular";
import {initializer} from "./app-init";
import {AppAuthGuard} from "./app.auth-guard";


const appRoutes: Routes = [

    {
        path: '',
        component: HomeComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['CUSTOMER'] }
    },
    {
        path: 'product',
        component: ProductComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN', 'CUSTOMER'] }
    },
    {
        path: 'product/create',
        component: ProductCreateComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN'] }
    },
    {
        path: 'product/:id',
        component: ProductCreateComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN', 'CUSTOMER'] }
    },
    {
        path: 'category',
        component: CategoryComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN', 'CUSTOMER'] }
    },
    {
        path: 'category/create',
        component: CategoryCreateComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN'] }
    },
    {
        path: 'category/:id',
        component: CategoryCreateComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN', 'CUSTOMER'] }
    },
    {
        path: 'order/create',
        component: OrderCreateComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN', 'CUSTOMER'] }
    },
    {
        path: 'order/copy/:id',
        component: OrderCreateComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN', 'CUSTOMER'] }
    },
    {
        path: 'order/:id',
        component: OrderCreateComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN', 'CUSTOMER'] }
    },
    {
        path: 'order',
        component: OrderComponent,
        canActivate: [AppAuthGuard],
        data: { roles: ['ADMIN', 'CUSTOMER'] }
    }

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
        MaterialFileInputModule,
        KeycloakAngularModule
    ],
    providers: [
        AppAuthGuard,
        {
            provide: APP_INITIALIZER,
            useFactory: initializer,
            multi: true,
            deps: [KeycloakService]
        }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
