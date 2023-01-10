import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ReportHinterleitnerPage } from './report-hinterleitner.page';

const routes: Routes = [
  {
    path: '',
    component: ReportHinterleitnerPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReportHinterleitnerPageRoutingModule {}
