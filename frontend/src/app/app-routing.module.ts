import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import {LoggedInGuard} from "./guards/logged-in.guard";
import {LoggedOutGuard} from "./guards/logged-out.guard";

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadChildren: () => import('./pages/home/home.module').then(m => m.HomePageModule)
  },
  {
    path: 'register',
    canLoad: [LoggedOutGuard],
    canActivate: [LoggedOutGuard],
    loadChildren: () => import('./pages/register/register.module').then(m => m.RegisterPageModule)
  },
  {
    path: 'login',
    canLoad: [LoggedOutGuard],
    canActivate: [LoggedOutGuard],
    loadChildren: () => import('./pages/login/login.module').then(m => m.LoginPageModule)
  },
  {
    path: 'borrow',
    canActivate: [LoggedInGuard],
    canLoad: [LoggedInGuard],
    loadChildren: () => import('./pages/borrow/borrow.module').then(m => m.BorrowPageModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
