import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserLogin } from 'src/app/login/login.component';
import { UserRegister } from 'src/app/register-user/register-user.component';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  jwttoken = ''

  constructor(
    private http : HttpClient
  ) { 
  }

  authenticate(user : UserLogin) {

    return this.http.post("http://localhost:8080/authenticate", user, {responseType : 'text' as 'json'})
  }

  authToken(token : string) {
    this.jwttoken = token
    sessionStorage.setItem('authToken', this.jwttoken)
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('authToken')
    return !(user === null)
  }

  logout() {
    sessionStorage.removeItem('authToken')
  }

  getUsername(userid : number) {
    return this.http.get<string>(`http://localhost:8080/getUsername/${userid}`, {responseType : 'text' as 'json'})
  }

  registerUser(user : UserRegister) {
    return this.http.post("http://localhost:8080/registeruser", user, {responseType : 'text' as 'json'})
  }

}
