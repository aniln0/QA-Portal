import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/data/user-service.service';

export class UserLogin {
  constructor(
    private emailid : string,
    private password : string
  ) {
  }
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = ''
  password = ''
  user!: UserLogin
  token = ''
  login_message = ''
  isInvalid=false

  constructor(
    private userService : UserService,
    private router : Router
  ) { 
  }

  ngOnInit(): void {
  }

  handleLogin() { 
    
    this.user = new UserLogin(this.username, this.password)
    this.userService.authenticate(this.user).subscribe(
      response => {
        this.handleSuccessfulResponse(response)
      },
      error => {
        this.handleErrorResponse(error.error)
      }
    )
  }
  handleSuccessfulResponse(response: Object) {
    this.token = response.toString() 
    this.userService.authToken(this.token)
    this.isInvalid = false
    this.router.navigate(['/home'])
  }

  handleErrorResponse(error : Object) {
    console.log(error)
    // this.login_message = error.toString()
    this.login_message = 'Invalid Credentials'
    this.isInvalid = true
  }

}
