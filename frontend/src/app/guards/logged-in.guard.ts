import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanLoad,
  Route,
  RouterStateSnapshot,
  UrlSegment,
  UrlTree
} from '@angular/router';
import { Observable } from 'rxjs';
import {UserService} from "../services/user.service";
import {ToastController} from "@ionic/angular";

@Injectable({
  providedIn: 'root'
})
export class LoggedInGuard implements CanActivate, CanLoad {

  constructor(private userService: UserService, private toastController: ToastController) {
  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    return this.userService.loggedIn();
  }

  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const loggedInPromise = this.userService.loggedIn();

    loggedInPromise.then(async value => {
      if (!value) {
        const toast = await this.toastController.create({
          message: "This page can only be accessed by users who are logged in!",
          duration: 2000,
          color: "danger"
        });
        await toast.present();
      }
    });

    return loggedInPromise;
  }
}
