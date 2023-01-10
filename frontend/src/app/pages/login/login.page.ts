import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Library} from "../../objects/library";
import {environment} from "../../../environments/environment";
import {firstValueFrom} from "rxjs";
import {User} from "../../objects/user";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";
import {LibraryService} from "../../services/library.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  email: string = "";

  constructor(private http: HttpClient, private userService: UserService, private libraryService: LibraryService, private router: Router) {
  }

  ngOnInit() {

  }

  async login() {
    const user = await firstValueFrom(this.http.get<User>(environment.apiUrl + "/users/login?email=" + this.email));
    const library = await firstValueFrom(this.http.get<Library>(environment.apiUrl + "/library/random?userId=" + user.id));

    console.log("is employee: " + user.isEmployee)

    await this.userService.login(user);
    await this.libraryService.set(library);
  }
}
