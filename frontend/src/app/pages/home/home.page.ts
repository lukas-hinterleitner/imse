import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {firstValueFrom} from "rxjs";
import {ToastController} from "@ionic/angular";
import {UserService} from "../../services/user.service";
import {LibraryService} from "../../services/library.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  constructor(private http: HttpClient, private toastController: ToastController, public userService: UserService, private libraryService: LibraryService,
              private router: Router) { }

  ngOnInit() {
    console.log(environment.apiUrl);
  }

  async migrate() {
    await firstValueFrom(this.http.get(environment.apiUrl + "/migrate"))
      .then(async value => {
        const toast = await this.toastController.create({
          message: `"migration to mongo db successful!`,
          duration: 2500,
          color: "success"
        });
        await toast.present();
      })
      .catch(async reason => {
        const toast = await this.toastController.create({
          message: "migration to mongo db failed!",
          duration: 2500,
          color: "danger"
        });
        await toast.present();
      });
  }

  async import() {
    await firstValueFrom(this.http.get(environment.apiUrl + "/import"))
      .then(async value => {
        const toast = await this.toastController.create({
          message: `"import successful!`,
          duration: 2000,
          color: "success"
        });
        await toast.present();
      })
      .catch(async reason => {
        const toast = await this.toastController.create({
          message: "import failed!",
          duration: 2000,
          color: "danger"
        });
        await toast.present();
      });
  }

  async logout() {
    await this.userService.logout();
    await this.libraryService.delete();

    await this.router.navigate(["/login"])
  }
}
