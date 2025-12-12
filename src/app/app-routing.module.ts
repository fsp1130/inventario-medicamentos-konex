import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MedicamentosListComponent } from './features/medicamentos/components/medicamentos-list/medicamentos-list.component';
import { VentasListComponent } from './features/ventas/components/ventas-list/ventas-list.component';

const routes: Routes = [
  { path: '', redirectTo: '/medicamentos', pathMatch: 'full' },
  { path: 'medicamentos', component: MedicamentosListComponent },
  { path: 'ventas', component: VentasListComponent },
  { path: '**', redirectTo: '/medicamentos' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }