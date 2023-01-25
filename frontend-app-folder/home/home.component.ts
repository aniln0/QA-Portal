import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { UserService } from '../service/data/user-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(
    public userService : UserService,
    private router : Router  
    ) { }

    hideLoginMessage = true

  // ngOnInit(): void {
  // }

}
