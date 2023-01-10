import { Component, OnInit } from '@angular/core';
import {Book} from "../../objects/book";
import {UserService} from "../../services/user.service";
import {LibraryService} from "../../services/library.service";
import {Library} from "../../objects/library";
import {User} from "../../objects/user";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {firstValueFrom} from "rxjs";
import {RoomBook} from "../../objects/roomBook";
import {ToastController} from "@ionic/angular";

@Component({
  selector: 'app-borrow',
  templateUrl: './borrow.page.html',
  styleUrls: ['./borrow.page.scss'],
})
export class BorrowPage implements OnInit {

  public books : RoomBook[] = Array();

  library: Library | undefined = undefined;
  user: User | undefined = undefined;

  constructor(private userService: UserService, private libraryService: LibraryService, private router: Router, private http: HttpClient,
              private toastController: ToastController) { }

  async ngOnInit() {
    this.user = await this.userService.get();
    this.library = await this.libraryService.get();

    this.books = await firstValueFrom(this.http.get<RoomBook[]>(environment.apiUrl + "/library/" + this.library?.id + "/books"));

    console.log(this.books)
  }

  async borrow(roomBook: RoomBook) {
    await firstValueFrom(this.http.post<RoomBook[]>(environment.apiUrl + "/borrow", {
      bookId: roomBook.book.id,
      libraryId: this.library?.id,
      userId: this.user?.id
    })).then(async value => {
      this.books = value;
      const toast = await this.toastController.create({
        message: `"${roomBook.book.name}" was borrowed successfully`,
        duration: 2000,
        color: "success"
      });
      await toast.present();
    }).catch(async reason => {
      const toast = await this.toastController.create({
        message: reason.toString(),
        duration: 2000,
        color: "danger"
      });
      await toast.present();
    });
  }
}
