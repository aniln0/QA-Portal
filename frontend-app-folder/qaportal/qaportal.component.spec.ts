import { DebugElement } from '@angular/core';
import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { of } from 'rxjs/internal/observable/of';
import { AnswerService } from '../service/data/answer.service';
import { click, QAService } from '../service/data/qa.service';
import { UserService } from '../service/data/user-service.service';

import { Answer, QaportalComponent, Questions } from './qaportal.component';

fdescribe('QaportalComponent', () => {
  let component: QaportalComponent
  let fixture: ComponentFixture<QaportalComponent>
  let element: DebugElement
  let qaService: any
  let answerService: any
  let questions: Questions[] = []
  questions.push(
    new Questions(28, 'Unable to load application',
      'When I try to run the Spring boot application, it is showing failed to launch',
      23, '23-11-2022', 'vishal@gmail.com')
  )
  let answers: Answer[] = []
  answers.push(
    new Answer(46, 'Any Answers?', 28, 23, '23-11-2022', 'vishal@gmail.com')
  )

  beforeEach(waitForAsync(() => {

    const qaServiceSpy = jasmine.createSpyObj('QAService', ['getAllQuestions', 'postQuestion'])
    const answerServiceSpy = jasmine.createSpyObj('AnswerService', ['getAnswerByQuestionId', 'postAnswer'])
    const userServiceSpy = jasmine.createSpyObj('UserService', ['authenticate', 'authToken'])

    TestBed.configureTestingModule({
      declarations: [
        QaportalComponent
      ],
      providers: [
        { provide: QAService, useValue: qaServiceSpy },
        { provide: AnswerService, useValue: answerServiceSpy },
        { provide: UserService, useValue: userServiceSpy }
      ]
    }).compileComponents()
      .then(() => {
        fixture = TestBed.createComponent(QaportalComponent)
        component = fixture.componentInstance
        element = fixture.debugElement
        qaService = TestBed.inject(QAService)
        answerService = TestBed.inject(AnswerService)
      })
  }))

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should display card header of first question', () => {
    qaService.getAllQuestions.and.returnValue(of(questions))
    fixture.detectChanges()
    const cards = element.query(By.css('.card-header'))
    expect(cards.nativeElement.textContent).toBe(' 28 - Unable to load application')
  })

  fit('should display all answers for a question', () => {
    qaService.getAllQuestions.and.returnValue(of(questions))
    answerService.getAnswerByQuestionId.and.returnValue(of(answers))
    fixture.detectChanges()
    const showAnswersButton = element.query(By.css('.btn'))
    console.log(showAnswersButton)
    click(showAnswersButton)
    const answers = element.queryAll(By.css('.'))
    
  })

});
