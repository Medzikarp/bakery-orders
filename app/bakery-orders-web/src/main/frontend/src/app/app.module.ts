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

const appRoutes: Routes = [
  {path: '', component: HomeComponent, data: {title: 'heroes'}},
  {path: 'product', component: ProductComponent, data: {title: 'Products'}}
];

@NgModule({
  declarations: [
    AppComponent,
    ProductComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MaterialModule,
    FlexLayoutModule,
    RouterModule.forRoot(
      appRoutes
    ),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
