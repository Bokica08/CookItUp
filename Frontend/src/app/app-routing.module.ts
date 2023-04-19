import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { LoginComponent } from './components/login/login.component';
import { UserInfoComponent } from './components/user-info/user-info.component';
import { dataResolverGetAdmin, dataResolverLoggedIn } from './resolver/dataResolverService';

const routes: Routes = [
  {path:'', component: HomeComponent},
  {path:'recipes', component: RecipesComponent},
  {
    path:'login',component:LoginComponent
  },
  {
    path:'info',component:UserInfoComponent,resolve:{data5:dataResolverLoggedIn,data6:dataResolverGetAdmin}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 

}
