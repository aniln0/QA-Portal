import { HttpClient, HttpHeaders } from '@angular/common/http';
import { DebugElement, Injectable } from '@angular/core';
import { Questions } from 'src/app/qaportal/qaportal.component';
import { UserService } from './user-service.service';

@Injectable({
  providedIn: 'root'
})
export class QAService {

  constructor(
    private http: HttpClient,
    private userService: UserService
  ) { }

  getAllQuestions() {
    let token = sessionStorage.getItem('authToken')
    let jwtTokenStr = 'Bearer ' + token
    const headers = new HttpHeaders().set("Authorization", jwtTokenStr)
    return this.http.get<Questions[]>('http://localhost:8081/getallquestions', { headers })
  }

  postQuestion(question: Questions) {
    let token = sessionStorage.getItem('authToken')
    let jwtTokenStr = 'Bearer ' + token
    const headers = new HttpHeaders().set("Authorization", jwtTokenStr)
    return this.http.post('http://localhost:8081/postquestion', question, { headers })
  }

}

export const ButtonClickEvents = {
  left: { button: 0 },
  right: { button: 2 }
};

/** Simulate element click. Defaults to mouse left-button click event. */
export function click(el: DebugElement | HTMLElement, eventObj: any = ButtonClickEvents.left): void {
if (el instanceof HTMLElement) {
  el.click();
} else {
  el.triggerEventHandler('click', eventObj);
}
}
