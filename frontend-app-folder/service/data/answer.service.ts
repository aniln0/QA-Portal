import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Answer, Questions } from 'src/app/qaportal/qaportal.component';
import { UserService } from './user-service.service';

@Injectable({
  providedIn: 'root'
})
export class AnswerService {

  constructor(
    private http : HttpClient,
    private userService : UserService
  ) { }

  getAnswerByQuestionId(questionid : number) {
    let token = sessionStorage.getItem('authToken')
    let jwtTokenStr = 'Bearer ' + token
    const headers = new HttpHeaders().set("Authorization", jwtTokenStr)
    return this.http.get<Answer[]>(`http://localhost:8082/getanswers/${questionid}`, {headers})
  }

  postAnswer(answer : Answer) {
    let token = sessionStorage.getItem('authToken')
    let jwtTokenStr = 'Bearer ' + token
    const headers = new HttpHeaders().set("Authorization", jwtTokenStr)
    
    return this.http.post(`http://localhost:8082/postanswer`, answer, {headers, responseType : 'text' as 'json'})
  }

}
