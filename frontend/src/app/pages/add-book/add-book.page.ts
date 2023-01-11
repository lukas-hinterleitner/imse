import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {firstValueFrom} from "rxjs";
import {Library} from "../../objects/library";
import {LibraryService} from "../../services/library.service";
import {Book} from "../../objects/book";
import {Room} from "../../objects/room";
import {ToastController} from "@ionic/angular";
import {library} from "ionicons/icons";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.page.html',
  styleUrls: ['./add-book.page.scss'],
})
export class AddBookPage implements OnInit {

  bookName: string = "";
  nameAlreadyEntered: boolean = false;
  bookExists: boolean = false;
  quantity: number | undefined = undefined;
  amountPages: number | undefined = undefined;

  library: Library | undefined = undefined;
  rooms: Room[] = Array();

  selectedRoom: Room | undefined = undefined;

  existingBook: Book | undefined = undefined;

  constructor(private http: HttpClient, private libraryService: LibraryService, private toastController: ToastController,
              private router: Router) { }

  async ngOnInit() {
    this.library = await this.libraryService.get();
  }

  async nameEntered() {
    if (!this.library) {
      this.library = await this.libraryService.get();
    }

    await firstValueFrom(this.http.get<Book>(environment.apiUrl + "/books?name=" + this.bookName))
      .then(value => {
        this.bookExists = true;
        this.existingBook = value;
      }).catch(reason => {
        console.log("doesn't exist")
      });

    this.nameAlreadyEntered = true;

    this.rooms = await firstValueFrom(this.http.get<Room[]>(environment.apiUrl + "/library/" + this.library?.id + "/rooms"));
  }

  compareWith(o1: any, o2: any) {
    return o1 && o2 ? o1.id === o2.id : o1 === o2;
  }

  roomChanged(ev: any) {
    this.selectedRoom = ev.target.value;
    console.log(this.selectedRoom);
  }

  async addBook() {
    await firstValueFrom(this.http.post(environment.apiUrl + "/books", {
      name: this.bookName,
      amountPages: this.amountPages,
      quantity: this.quantity,
      roomId: this.selectedRoom?.id,
      libraryId: this.library?.id
    })).then(async value => {
      const toast = await this.toastController.create({
        message: "Book successfully delivered!",
        duration: 2500,
        color: "success"
      });
      await toast.present();
    }).catch(reason => {
      console.log(reason);
    });

    await this.router.navigate(['/add-book']);
  }

  async addExistingBook() {
    await firstValueFrom(this.http.put(environment.apiUrl + "/books/?libraryId=" + this.library?.id, {
      bookId: this.existingBook?.id,
      quantity: this.quantity
    })).then(async value => {
      const toast = await this.toastController.create({
        message: "Book successfully delivered!",
        duration: 2500,
        color: "success"
      });
      await toast.present();
    }).catch(reason => {
      console.log(reason);
    })

    await this.router.navigate(['/add-book']);
  }
}
