import { Component, OnInit } from '@angular/core';
import {Book} from "../../objects/book";
import {UserService} from "../../services/user.service";
import {LibraryService} from "../../services/library.service";
import {Library} from "../../objects/library";
import {User} from "../../objects/user";
import {Router} from "@angular/router";

@Component({
  selector: 'app-borrow',
  templateUrl: './borrow.page.html',
  styleUrls: ['./borrow.page.scss'],
})
export class BorrowPage implements OnInit {

  public books : Book[] = Array();

  library: Library | undefined = undefined;
  user: User | undefined = undefined;

  constructor(private userService: UserService, private libraryService: LibraryService, private router: Router) { }

  async ngOnInit() {
    console.log("borrow init");
    if (!(await this.userService.loggedIn() || await this.libraryService.available())) {
      //await this.router.navigate(["/login"]);
    }

    this.user = await this.userService.get();

    console.log(this.user);

    for (let i = 0; i < 100; i++) {
      const newBook = new Book();
      newBook.id = 1;
      newBook.name = "Harry Potter";
      newBook.amountOfPages = 420;
      newBook.quantity = 20;

      this.books.push(newBook);
    }
  }

  async logout() {
    await this.userService.logout();
    await this.libraryService.delete();

    await this.router.navigate(["/login"])
  }
}
