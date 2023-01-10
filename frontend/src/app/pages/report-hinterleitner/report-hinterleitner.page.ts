import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {firstValueFrom} from "rxjs";
import {logoGoogle} from "ionicons/icons";

@Component({
  selector: 'app-report-hinterleitner',
  templateUrl: './report-hinterleitner.page.html',
  styleUrls: ['./report-hinterleitner.page.scss'],
})
export class ReportHinterleitnerPage implements OnInit {

  public libraries: any[] = Array();

  constructor(private http: HttpClient) { }

  async ngOnInit() {
    this.libraries = await firstValueFrom(this.http.get<Object[]>(environment.apiUrl + "/library/report-hinterleitner"));
    console.log(this.libraries)
  }

}
