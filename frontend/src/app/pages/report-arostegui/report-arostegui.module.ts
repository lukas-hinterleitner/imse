import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ReportArosteguiPageRoutingModule } from './report-arostegui-routing.module';

import { ReportArosteguiPage } from './report-arostegui.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReportArosteguiPageRoutingModule
  ],
  declarations: [ReportArosteguiPage]
})
export class ReportArosteguiPageModule {}
