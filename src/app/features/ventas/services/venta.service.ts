import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Venta, VentaRequest } from '../../../core/models/venta.model';
import { Page } from '../../../core/models/page.model';
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VentaService {
  private apiUrl = `${environment.apiUrl}/ventas`;

  constructor(private http: HttpClient) {}

  realizar(venta: VentaRequest): Observable<Venta> {
    return this.http.post<Venta>(this.apiUrl, venta);
  }

  listar(page: number = 0, size: number = 10): Observable<Page<Venta>> {
    const params = new HttpParams()
      .set('page', page.toString())
      .set('size', size.toString());
    return this.http.get<Page<Venta>>(this.apiUrl, { params });
  }

  filtrarPorFechas(fechaInicio: string, fechaFin: string, page: number = 0, size: number = 10): Observable<Page<Venta>> {
    const params = new HttpParams()
      .set('fechaInicio', fechaInicio)
      .set('fechaFin', fechaFin)
      .set('page', page.toString())
      .set('size', size.toString());
    
    return this.http.get<Page<Venta>>(`${this.apiUrl}/filtrar`, { params });
  }
}