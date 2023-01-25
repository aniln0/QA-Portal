import { TestBed } from '@angular/core/testing';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';

import { AnswerService } from './answer.service';
import { Answer } from 'src/app/qaportal/qaportal.component';

describe('AnswerService', () => {
  let service: AnswerService,
      mockHttpController : HttpTestingController,
      testData : Answer[] = []
      testData.push(
        new Answer(1,'First Answer', 1, 1, '19-11-2022', 'prakash')
      )

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      providers: [
        AnswerService
      ]
    });
    service = TestBed.inject(AnswerService);
    mockHttpController = TestBed.inject(HttpTestingController)
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve all answers for a given questionid', () => {
    service.getAnswerByQuestionId(1).subscribe(
      answers => {
        expect(answers).toBeTruthy()
        expect(answers.length).toBe(1)
        let answer = answers.find(answer => answer.answerid == 1)
        expect(answer?.answer).toBe('First Answer')
      })
    
      const req = mockHttpController.expectOne('http://localhost:8082/getanswers/1')

      expect(req.request.method).toEqual('GET')
      req.flush(testData)

  })

  it('should save an answer', () => {
    service.postAnswer(testData[0]).subscribe()
    
    const req = mockHttpController.expectOne('http://localhost:8082/postanswer')

    expect(req.request.method).toEqual('POST')
  })


});
