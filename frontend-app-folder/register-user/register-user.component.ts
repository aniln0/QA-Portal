import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/data/user-service.service';

export class UserRegister {
  constructor(
    public userid: number,
    public firstname: string,
    public lastname: string,
    public emailid: string,
    public password: string,
    public createdate: string
  ) { }
}

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {

  user!: UserRegister

  invalid_fn_message = ''
  invalid_ln_message = ''
  invalid_email_message = ''
  invalid_pass_message = ''

  showLoginButton = false

  registerSuccessful = false

  constructor(
    private userService : UserService
  ) { }

  ngOnInit(): void {
    this.user = new UserRegister(0, '', '', '', '', '')
  }

  handleRegister() {

    this.invalid_fn_message = ''
    this.invalid_ln_message = ''
    this.invalid_email_message = ''
    this.invalid_pass_message = ''

    if (this.user.firstname.length < 4)
      this.invalid_fn_message = 'Should be greater than 3 characters'

    if (this.user.firstname.match("^[a-zA-Z ]*$") == null)
      this.invalid_fn_message = 'Should Contain only alphabets.'

    if (this.user.lastname.length < 4)
      this.invalid_ln_message = 'Should be greater than 3 characters'

    if (this.user.lastname.match("^[a-zA-Z ]*$") == null)
      this.invalid_ln_message = 'Should Contain only alphabets.'

    if (this.user.emailid.match("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}") == null)
      this.invalid_email_message = 'Email should be a combination of atleast 1 number'

    if (this.user.password.match("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$") == null)
      this.invalid_pass_message = 'Password should be a combination of atleast 1 number, 1 Captial Letter, a symbol of (@#$)'

    if(this.invalid_fn_message == '' && this.invalid_ln_message == '' 
       && this.invalid_email_message == '' &&  this.invalid_pass_message == '') {

        this.userService.registerUser(this.user).subscribe(
          response => {
            this.registerSuccessful = true
            this.showLoginButton = true
          },
          error => {
            this.registerSuccessful = false
          }
        )

       }

  }

}
