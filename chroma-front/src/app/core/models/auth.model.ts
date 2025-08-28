export interface LoginRequest {
  username: string;
  password: string;
}

export interface AuthResponse {
  token: string;
  expiresIn: number;
}

export interface User {
  id: number;
  username: string;
  email: string;
  roles: string[];
}