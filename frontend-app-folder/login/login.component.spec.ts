import { DebugElement } from '@angular/core';
import { ComponentFixture, fakeAsync, TestBed, tick, waitForAsync } from '@angular/core/testing';
import { By, Title } from '@angular/platform-browser';
import { of } from 'rxjs';
import { UserService } from '../service/data/user-service.service';

import { LoginComponent, UserLogin } from './login.component';

describe('LoginComponent', () => {

  let component: LoginComponent
  let fixture: ComponentFixture<LoginComponent>
  let element: DebugElement
  let userService : any
  let userLogin : UserLogin
  userLogin = new UserLogin('vishal@gmail.com', 'Password')
  
  beforeEach(waitForAsync(() => {

    const userServiceSpy = jasmine.createSpyObj('UserService', ['authenticate', 'authToken'])

    TestBed.configureTestingModule({
      declarations: [
        LoginComponent
      ],
      providers: [
        {provide: UserService, useValue: userServiceSpy}
      ]
    }).compileComponents()
      .then(() => {
        fixture = TestBed.createComponent(LoginComponent)
        component = fixture.componentInstance
        element = fixture.debugElement
        userService = TestBed.inject(UserService)
      })
  }))

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Login Title should be displayed', () => {
    const title = element.query(By.css(".card-header"))
    expect(title.nativeElement.textContent).toBe('Login')
  })

  it('handleLogin() should be called', () => {

    spyOn(component, 'handleLogin')
    const loginButton = element.query(By.css('.btn'))
    loginButton.triggerEventHandler('click', null)
    expect(component.handleLogin).toHaveBeenCalled()

  })

});
