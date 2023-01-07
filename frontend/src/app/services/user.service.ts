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
    await this.router.navigate(["/borrow"])
  }

  async logout() {
    await this.storage.remove(this.userKey);
    await this.router.navigate(["/login"])
  }

  async get() {
    return await this.storage.get(this.userKey);
  }

  async loggedIn() {
    return (await this.storage.keys()).includes(this.userKey);
  }

  async loggedOut() {
    return !(await this.storage.keys()).includes(this.userKey);
  }
}
