import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {firstValueFrom} from "rxjs";
import {ToastController} from "@ionic/angular";

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  constructor(private http: HttpClient, private toastController: ToastController) { }

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
}
