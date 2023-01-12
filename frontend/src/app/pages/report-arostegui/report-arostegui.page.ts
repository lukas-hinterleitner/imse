import { Component, OnInit } from '@angular/core';
import {firstValueFrom} from "rxjs";
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-report-arostegui',
  templateUrl: './report-arostegui.page.html',
  styleUrls: ['./report-arostegui.page.scss'],
})
export class ReportArosteguiPage implements OnInit {

  public libraries: any[] = Array();

  constructor(private http: HttpClient) { }

  async ngOnInit() {
    this.libraries = await firstValueFrom(this.http.get<Object[]>(environment.apiUrl + "/borrow/report-arostegui"));
    console.log(this.libraries)
  }

}
