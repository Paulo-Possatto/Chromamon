import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  private apiUrl = 'http://localhost:8080/api/v1/reports';

  constructor(private http: HttpClient) { }

  generateAnalysisReport(analysisId: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/analysis/${analysisId}`, {
      responseType: 'blob'
    });
  }

  generateTransformerReport(transformerId: number): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/transformer/${transformerId}`, {
      responseType: 'blob'
    });
  }
}