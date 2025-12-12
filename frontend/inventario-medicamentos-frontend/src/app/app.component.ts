import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'Sistema de Inventario Konex';
  menuItems: MenuItem[] = [];
  currentYear = new Date().getFullYear();
  
  constructor(private router: Router) {}

  ngOnInit(): void {
    this.initializeMenu();
    this.highlightActiveRoute();
  }

  private initializeMenu(): void {
    this.menuItems = [
      {
        label: 'Medicamentos',
        icon: 'pi pi-fw pi-box',
        routerLink: ['/medicamentos'],
        styleClass: 'menu-item-medicamentos'
      },
      {
        label: 'Ventas',
        icon: 'pi pi-fw pi-shopping-cart',
        routerLink: ['/ventas'],
        styleClass: 'menu-item-ventas'
      },
      {
        label: 'Reportes',
        icon: 'pi pi-fw pi-chart-bar',
        items: [
          {
            label: 'Dashboard',
            icon: 'pi pi-fw pi-chart-line',
            command: () => this.showComingSoon('Dashboard')
          },
          {
            label: 'Inventario',
            icon: 'pi pi-fw pi-list',
            command: () => this.showComingSoon('Reporte de Inventario')
          },
          {
            label: 'Ventas por período',
            icon: 'pi pi-fw pi-calendar',
            command: () => this.showComingSoon('Reporte de Ventas')
          }
        ]
      },
      {
        label: 'Ayuda',
        icon: 'pi pi-fw pi-question-circle',
        items: [
          {
            label: 'Documentación',
            icon: 'pi pi-fw pi-book',
            command: () => window.open('https://github.com', '_blank')
          },
          {
            label: 'Acerca de',
            icon: 'pi pi-fw pi-info-circle',
            command: () => this.showAbout()
          }
        ]
      }
    ];
  }

  private highlightActiveRoute(): void {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
      });
  }

  private showComingSoon(feature: string): void {
    alert(`${feature} - Próximamente disponible`);
  }

  private showAbout(): void {
    alert(`Sistema de Inventario de Medicamentos v1.0.0\nDesarrollado para Konex\n© ${this.currentYear}`);
  }
}