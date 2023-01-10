import { Component } from '@angular/core';
import {Storage} from "@ionic/storage-angular";

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.scss'],
})
export class AppComponent {
  public appPages = [
    { title: 'Home', url: "/home", icon: 'home'},
    { title: 'Register', url: "/register", icon: 'person-add'},
    { title: 'Login', url: "/login", icon: 'log-in'},
    { title: 'Borrow', url: "/borrow", icon: 'book'},
    { title: 'Add Book', url: "/add-book", icon: 'add'},
  ];

  public reportPages = [
    {title: 'Report Arostegui', url: "/report-arostegui", icon: "analytics"},
    {title: 'Report Hinterleitner', url: "/report-hinterleitner", icon: "analytics"}
  ]

  constructor(private storage: Storage) {
  }

  async ngOnInit() {
    await this.storage.create();
  }
}
