import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ReportArosteguiPage } from './report-arostegui.page';

const routes: Routes = [
  {
    path: '',
    component: ReportArosteguiPage
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReportArosteguiPageRoutingModule {}
