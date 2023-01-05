import { Injectable } from '@angular/core';
import {Storage} from "@ionic/storage-angular";
import {User} from "../objects/user";
import {Library} from "../objects/library";

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  private libraryKey: string = "LIBRARY";

  constructor(private storage: Storage) { }

  async set(library: Library) {
    await this.storage.set(this.libraryKey, library);
  }

  async get() {
    await this.storage.get(this.libraryKey);
  }

  async available() {
    return (await this.storage.keys()).includes(this.libraryKey);
  }

  async delete() {
    await this.storage.remove(this.libraryKey);
  }
}
