import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Diagnostic, DiagnosticMethod } from '../models/diagnostic.model';

@Injectable({
  providedIn: 'root'
})
export class DiagnosticService {
  private apiUrl = 'http://localhost:8080/api/v1/diagnostics';

  constructor(private http: HttpClient) { }

  generateDiagnostic(analysisId: number, method: DiagnosticMethod): Observable<Diagnostic> {
    return this.http.post<Diagnostic>(`${this.apiUrl}/generate`, {
      analysisId,
      method
    });
  }

  getByAnalysisId(analysisId: number): Observable<Diagnostic[]> {
    return this.http.get<Diagnostic[]>(`${this.apiUrl}/analysis/${analysisId}`);
  }
}