import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/medicamentos',
    pathMatch: 'full'
  },
  {
    path: 'medicamentos',
    loadComponent: () => import('./features/medicamentos/components/medicamentos-list/medicamentos-list.component')
      .then(m => m.MedicamentosListComponent)
  },
  {
    path: 'ventas',
    loadComponent: () => import('./features/ventas/components/ventas-list/ventas-list.component')
      .then(m => m.VentasListComponent)
  },
  {
    path: '**',
    redirectTo: '/medicamentos'
  }
];