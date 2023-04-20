import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { UrlnotfoundComponent } from './components/urlnotfound/urlnotfound.component';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'home',component: HomeComponent},
  {path:'recipes', component: RecipesComponent},
  {path:'category/:category', component: RecipesComponent},
  {path:'**', component: UrlnotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
