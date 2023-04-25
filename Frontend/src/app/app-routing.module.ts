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
import { AddReipeComponent } from './components/add-reipe/add-reipe.component';
import { MyRecipesComponent } from './components/my-recipes/my-recipes.component';
import { FavoriteRecipesComponent } from './components/favorite-recipes/favorite-recipes.component';

const routes: Routes = [
  {path:'', component: HomeComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'recipes', component: RecipesComponent},
  {path:'login',component:LoginComponent},
  {path:'profile',component:UserInfoComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'register',component:RegisterComponent},
  {path:'addRecipe',component:AddReipeComponent,resolve:{data:dataResolverGetCategories}},
  {path:'home',component: HomeComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'category/:category', component: RecipesComponent},
  {path:'search/:inputText', component: RecipesComponent},
  {path:'recipe/:id', component: RecipeDetailsComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'contact',component: ContactComponent},
  {path:'aboutUs', component: AboutUsComponent},
  {path:'faq', component: FaqComponent},
  {path:'myRecipes', component: MyRecipesComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'myFavoriteRecipes', component: FavoriteRecipesComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'**', component: UrlnotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
