import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Transformer, TransformerCreate } from '../models/transformer.model';

@Injectable({
  providedIn: 'root'
})
export class TransformerService {
  private apiUrl = 'http://localhost:8080/api/v1/transformers';

  constructor(private http: HttpClient) { }

  getAll(): Observable<Transformer[]> {
    return this.http.get<Transformer[]>(this.apiUrl);
  }

  getById(id: number): Observable<Transformer> {
    return this.http.get<Transformer>(`${this.apiUrl}/${id}`);
  }

  create(transformer: TransformerCreate): Observable<Transformer> {
    return this.http.post<Transformer>(this.apiUrl, transformer);
  }

  update(id: number, transformer: TransformerCreate): Observable<Transformer> {
    return this.http.put<Transformer>(`${this.apiUrl}/${id}`, transformer);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}