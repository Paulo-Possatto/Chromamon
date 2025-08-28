import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginRequest, AuthResponse, User } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/v1/auth';
  public currentUser = signal<User | null>(null);
  public isAuthenticatedSignal = signal(false);

  constructor(private http: HttpClient) {
    this.loadCurrentUser();
  }

  login(credentials: LoginRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          this.loadCurrentUser();
          this.isAuthenticatedSignal.set(true);
        })
      );
  }

  logout(): void {
    localStorage.removeItem('token');
    this.currentUser.set(null);
    this.isAuthenticatedSignal.set(false);
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem('token');
    return !!token;
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  private loadCurrentUser(): void {
    const token = this.getToken();
    if (token) {
      // Aqui você pode fazer uma chamada para obter os dados do usuário
      const user: User = {
        id: 1,
        username: 'admin',
        email: 'admin@example.com',
        roles: ['ADMIN']
      };
      this.currentUser.set(user);
      this.isAuthenticatedSignal.set(true);
    }
  }
}