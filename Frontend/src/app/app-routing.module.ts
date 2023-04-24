import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { UrlnotfoundComponent } from './components/urlnotfound/urlnotfound.component';
import { dataResolverGetAdmin, dataResolverLoggedIn } from './resolver/dataResolverService';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { LoginComponent } from './components/login/login.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { ContactComponent } from './components/contact/contact.component';
import { AboutUsComponent } from './components/about-us/about-us.component';
import { FaqComponent } from './components/faq/faq.component';
import { RegisterComponent } from './components/register/register.component';

const routes: Routes = [
  {path:'', component: HomeComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {path:'recipes', component: RecipesComponent},
  {path:'login',component:LoginComponent},
  {path:'info',component:UserInfoComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}},
  {
    path:'register',component:RegisterComponent
  },
  {path:'home',component: HomeComponent},
  {path:'category/:category', component: RecipesComponent},
  {path:'search/:inputText', component: RecipesComponent},
  {path:'recipe/:id', component: RecipeDetailsComponent},
  {path:'contact',component: ContactComponent},
  {path:'aboutUs', component: AboutUsComponent},
  {path:'faq', component: FaqComponent},
  {path:'**', component: UrlnotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
