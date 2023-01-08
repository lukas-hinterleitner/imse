import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {firstValueFrom} from "rxjs";
import {logIn} from "ionicons/icons";

@Component({
  selector: 'app-home',
  templateUrl: './home.page.html',
  styleUrls: ['./home.page.scss'],
})
export class HomePage implements OnInit {

  constructor(private http: HttpClient) { }

  ngOnInit() {
    console.log(environment.apiUrl);
  }

  async migrate() {
    await firstValueFrom(this.http.get(environment.apiUrl + "/migrate"))
      .then(value => console.log("migration success"))
      .catch(reason => console.log("migration failed"));

  }
}
