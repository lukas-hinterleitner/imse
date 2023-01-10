import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import {LoggedInGuard} from "./guards/logged-in.guard";
import {LoggedOutGuard} from "./guards/logged-out.guard";
import {EmployeeGuard} from "./guards/employee.guard";

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
  },
  {
    path: 'add-book',
    canActivate: [LoggedInGuard, EmployeeGuard],
    canLoad: [LoggedInGuard, EmployeeGuard],
    loadChildren: () => import('./pages/add-book/add-book.module').then( m => m.AddBookPageModule)
  },
  {
    path: 'report-hinterleitner',
    loadChildren: () => import('./pages/report-hinterleitner/report-hinterleitner.module').then( m => m.ReportHinterleitnerPageModule)
  },
  {
    path: 'report-arostegui',
    loadChildren: () => import('./pages/report-arostegui/report-arostegui.module').then( m => m.ReportArosteguiPageModule)
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
