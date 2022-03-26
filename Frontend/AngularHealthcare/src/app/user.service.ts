import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/api";
  constructor(private httpClient: HttpClient) { }
  loginUser(user: User): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}/login`, user);
  }
  createUser(user: User): Observable<Object>{
    return this.httpClient.post(`${this.baseUrl}/registration`,user);
    }
}
