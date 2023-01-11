import { Injectable } from '@angular/core';
import {Storage} from "@ionic/storage-angular";
import {Library} from "../objects/library";
import {BehaviorSubject, Observable, of, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LibraryService {

  private libraryKey: string = "LIBRARY";
  public libraryName: Subject<Library | null> = new BehaviorSubject<Library | null>(null);

  constructor(private storage: Storage) { }

  async set(library: Library) {
    await this.storage.set(this.libraryKey, library);
    this.libraryName.next(library);
  }

  async get(): Promise<Library> {
    return await this.storage.get(this.libraryKey);
  }

  async available() {
    return (await this.storage.keys()).includes(this.libraryKey);
  }

  async delete() {
    await this.storage.remove(this.libraryKey);
    this.libraryName.next(null);
  }
}
