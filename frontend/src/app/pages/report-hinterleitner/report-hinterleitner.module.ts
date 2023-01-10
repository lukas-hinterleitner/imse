import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { IonicModule } from '@ionic/angular';

import { ReportHinterleitnerPageRoutingModule } from './report-hinterleitner-routing.module';

import { ReportHinterleitnerPage } from './report-hinterleitner.page';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    ReportHinterleitnerPageRoutingModule
  ],
  declarations: [ReportHinterleitnerPage]
})
export class ReportHinterleitnerPageModule {}
