import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { UrlnotfoundComponent } from './components/urlnotfound/urlnotfound.component';
import { dataResolverGetAdmin, dataResolverLoggedIn, dataResolverGetCategories } from './resolver/dataResolverService';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { LoginComponent } from './components/login/login.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
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
import { AdminOrdersComponent } from './components/admin-orders/admin-orders.component';
import { EditRecipeComponent } from './components/edit-recipe/edit-recipe.component';
import { MyProfileComponent } from './components/my-profile/my-profile.component';

const routes: Routes = [
  {path:'', component: HomeComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'recipes', component: RecipesComponent},
  {path:'login',component:LoginComponent},
  {path:'profile',component:MyProfileComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'userInfo/:username',component:UserInfoComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'register',component:RegisterComponent},
  {path:'addRecipe',component:AddRecipeComponent,resolve:{data:dataResolverGetCategories}},
  {path:'addOrder/:id/:numPersons',component:AddOrderComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'home',component: HomeComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'category/:category', component: RecipesComponent},
  {path:'search/:inputText', component: RecipesComponent},
  {path:'userRecipes/:username', component: RecipesComponent},
  {path:'recipe/:id', component: RecipeDetailsComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'edit/:id',component:EditRecipeComponent,resolve:{data:dataResolverGetCategories}},
  {path:'contact',component: ContactComponent},
  {path:'aboutUs', component: AboutUsComponent},
  {path:'faq', component: FaqComponent},
  {path:'addIngredient',component:AddIngredientComponent, resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'addCategory',component:AddCategoryComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'approveAdmin',component:ApproveAdminComponent},
  {path:'adminOrders',component:AdminOrdersComponent},
  {path:'myReviews',component:MyReviewsComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'myRecipes', component: MyRecipesComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'myOrders', component: MyOrdersComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'myFavoriteRecipes', component: FavoriteRecipesComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'**', component: UrlnotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
