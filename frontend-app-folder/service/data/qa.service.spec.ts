import { TestBed } from '@angular/core/testing';
import { HttpTestingController, HttpClientTestingModule } from '@angular/common/http/testing';
import { QAService } from './qa.service';
import { Questions } from 'src/app/qaportal/qaportal.component';

describe('QAService', () => {
  let service: QAService,
      mockHttpController : HttpTestingController,
      testData : Questions[] = []
      testData.push(
        new Questions(1,'First Question','Question Description', 1, '19-11-2022', 'prakash')
      )

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
      ],
      providers: [
        QAService,
      ]
    });

    service = TestBed.inject(QAService);
    mockHttpController = TestBed.inject(HttpTestingController)
    
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should retrieve all questions', () => {
    service.getAllQuestions().subscribe(
      questions => {
        expect(questions).toBeTruthy()
        expect(questions.length).toBe(1)
        let question = questions.find(question => question.userid == 1)
        expect(question?.description).toBe('Question Description')
      })
    
      const req = mockHttpController.expectOne('http://localhost:8081/getallquestions')

      expect(req.request.method).toEqual('GET')
      req.flush(testData)

  })

  it('should save a question', () => {
    service.postQuestion(testData[0]).subscribe()
    
    const req = mockHttpController.expectOne('http://localhost:8081/postquestion')

    expect(req.request.method).toEqual('POST')
  })

});
