import { Component, OnInit } from '@angular/core';
import {firstValueFrom} from "rxjs";
import {User} from "../../objects/user";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../../services/user.service";
import {LibraryService} from "../../services/library.service";
import {Library} from "../../objects/library";

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  name: string = "";
  email: string = "";
  phone: string = "";


  constructor(private http: HttpClient, private userService: UserService, private libraryService: LibraryService) { }

  ngOnInit() {
  }

  async registerUser() {
    const user = await firstValueFrom(this.http.post<User>(environment.apiUrl + "/users", {name: this.name, email: this.email, phoneNumber: this.phone}));
    const library = await firstValueFrom(this.http.get<Library>(environment.apiUrl + "/library/random"));

    await this.libraryService.set(library);
    await this.userService.login(user);
  }

}
