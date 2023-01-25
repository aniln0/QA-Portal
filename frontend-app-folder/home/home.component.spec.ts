import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { UserService } from '../service/data/user-service.service';

import { HomeComponent } from './home.component';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CssSelector } from '@angular/compiler';

describe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ HomeComponent ],
      providers: [
        UserService
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Check If QA Portal Heading Created', () => {
    const data = fixture.nativeElement
    expect(data.querySelector('.display-4').textContent).toBe('QA Portal')
  })

});

