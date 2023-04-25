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
import { AddReipeComponent } from './components/add-reipe/add-reipe.component';
import { MyRecipesComponent } from './components/my-recipes/my-recipes.component';
import { FavoriteRecipesComponent } from './components/favorite-recipes/favorite-recipes.component';
import { ApproveAdminComponent } from './components/approve-admin/approve-admin.component';



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
    AddReipeComponent,
    RecipeDetailsComponent,
    AboutUsComponent,
    ContactComponent,
    FaqComponent,
    RegisterComponent,
    MyRecipesComponent,
    FavoriteRecipesComponent,
    ApproveAdminComponent
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
