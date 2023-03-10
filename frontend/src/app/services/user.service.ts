import { Injectable } from '@angular/core';
import {Storage} from "@ionic/storage-angular";
import {User} from "../objects/user";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userKey: string = "USER";

  constructor(private storage: Storage, private router: Router) { }

  async login(user: User) {
    await this.storage.set(this.userKey, user);

    if (user.isEmployee) {
      await this.router.navigate(["/add-book"])
    } else {
      await this.router.navigate(["/borrow"])
    }
  }

  async logout() {
    await this.storage.remove(this.userKey);
    await this.router.navigate(["/login"])
  }

  async get(): Promise<User> {
    return await this.storage.get(this.userKey);
  }

  async isEmployee(): Promise<boolean> {
    const user = await this.get();

    if (user) {
      return user.isEmployee;
    } else {
      return false;
    }
  }

  async loggedIn() {
    return (await this.storage.keys()).includes(this.userKey);
  }

  async loggedOut() {
    return !(await this.storage.keys()).includes(this.userKey);
  }
}
