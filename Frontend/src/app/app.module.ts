import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/home/home.component';
import { RecipesComponent } from './components/recipes/recipes.component';

import { UrlnotfoundComponent } from './components/urlnotfound/urlnotfound.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';

import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { httpInterceptorProviders } from './_helpers/http.interceptor';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { ContactComponent } from './components/contact/contact.component';
import { FaqComponent } from './components/faq/faq.component';
import { RegisterComponent } from './components/register/register.component';
import { AddRecipeComponent } from './components/add-recipe/add-recipe.component';
import { MyRecipesComponent } from './components/my-recipes/my-recipes.component';
import { FavoriteRecipesComponent } from './components/favorite-recipes/favorite-recipes.component';
import { ApproveAdminComponent } from './components/approve-admin/approve-admin.component';
import { MyReviewsComponent } from './components/my-reviews/my-reviews.component';
import { AddCategoryComponent } from './components/add-category/add-category.component';
import { AddOrderComponent } from './components/add-order/add-order.component';
import { AddIngredientComponent } from './components/add-ingredient/add-ingredient.component';
import { MyOrdersComponent } from './components/my-orders/my-orders.component';
import { AdminRecipesComponent } from './components/admin-recipes/admin-recipes.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RecipesComponent,
    UrlnotfoundComponent,
    NavbarComponent,
    FooterComponent,
    LoginComponent,
    UserInfoComponent,
    AddRecipeComponent,
    RecipeDetailsComponent,
    AboutUsComponent,
    ContactComponent,
    FaqComponent,
    RegisterComponent,
    MyRecipesComponent,
    FavoriteRecipesComponent,
    ApproveAdminComponent,
    MyReviewsComponent,
    AddCategoryComponent,
    AddOrderComponent,
    AddIngredientComponent,
    MyOrdersComponent,
    AdminRecipesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
    ],
  providers: [ httpInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
