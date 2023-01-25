import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { UserLogin } from 'src/app/login/login.component';
import { RegisterUserComponent, UserRegister } from 'src/app/register-user/register-user.component';

import { UserService } from './user-service.service';

describe('UserService', () => {
  let service: UserService,
      mockHttpController : HttpTestingController,
      testUserRegisterData : UserRegister
      testUserRegisterData = new UserRegister(1, 'Shiva', 'Kishore', 'shiva@gmail.com', 'Shiva@1234', '19-11-2022')
      let testUserLoginData = new UserLogin('user1@gmail.com', "User@1234")
      

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ],
      providers: [
        UserService,
      ]
    });

    service = TestBed.inject(UserService);
    mockHttpController = TestBed.inject(HttpTestingController)

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('User should be authenticated', () => {
    service.authenticate(testUserLoginData).subscribe(
      repsonse => {
        expect(repsonse).toBeTruthy()
        expect(repsonse).toBe('jwt_token')
      }
    )

    const req = mockHttpController.expectOne('http://localhost:8080/authenticate')
    expect(req.request.method).toEqual('POST')
    req.flush('jwt_token')

  })

  it('Username should be generated', () => {

    service.getUsername(1).subscribe(
      username => {
        expect(username).toBe('shiva@gmail.com')
      }
    )

    const req = mockHttpController.expectOne('http://localhost:8080/getUsername/1')
    
    expect(req.request.method).toEqual('GET')
    expect(req.request.params.get('userid'))

    req.flush('shiva@gmail.com')
    
  })

  it('User should be registered' ,() => {

    const registerSuccessful = true
    service.registerUser(testUserRegisterData).subscribe(
      response => {
          expect(registerSuccessful).toBe(true)
      }
    )

    const req = mockHttpController.expectOne('http://localhost:8080/registeruser')

    expect(req.request.method).toEqual('POST')

  })

});
