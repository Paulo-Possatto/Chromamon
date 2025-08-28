import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Analysis, AnalysisCreate } from '../models/analysis.model';

@Injectable({
  providedIn: 'root'
})
export class AnalysisService {
  private apiUrl = 'http://localhost:8080/api/v1/analyses';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Analysis[]> {
    return this.http.get<Analysis[]>(this.apiUrl);
  }

  getById(id: number): Observable<Analysis> {
    return this.http.get<Analysis>(`${this.apiUrl}/${id}`);
  }

  create(analysis: AnalysisCreate): Observable<Analysis> {
    return this.http.post<Analysis>(this.apiUrl, analysis);
  }

  update(id: number, analysis: AnalysisCreate): Observable<Analysis> {
    return this.http.put<Analysis>(`${this.apiUrl}/${id}`, analysis);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}