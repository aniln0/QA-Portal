import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AnswerService } from '../service/data/answer.service';
import { QAService } from '../service/data/qa.service';
import { UserService } from '../service/data/user-service.service';

export class Questions {

  constructor(
    public questionid: number,
    public title: string,
    public description: string,
    public userid: number,
    public createdate: string,
    public username : string
  ) { }
}

export class Answer {
  constructor(
    public answerid : number,
    public answer : string,
    public questionid : number,
    public userid : number,
    public createdate : string,
    public username : string
  ) {}
}

@Component({
  selector: 'app-qaportal',
  templateUrl: './qaportal.component.html',
  styleUrls: ['./qaportal.component.css']
})

export class QaportalComponent implements OnInit {

  questions: Questions[]

  question!: Questions

  answers: Answer[] = []

  userAnswer = ''

  userPostedAnswer! : Answer

  isAnswersPosted = true

  isValidResponse = true

  message = ''

  postAnswerSuccessful = false

  invalidAnswer = ''

  constructor(
    private qaService: QAService,
    private router: Router,
    private answerService : AnswerService,
    private userService : UserService
  ) {
    this.questions = []
  }

  ngOnInit(): void {
    this.showAllQuestions()
  }

  showAllQuestions() {
    this.postAnswerSuccessful = false
    this.qaService.getAllQuestions().subscribe(
      response => {
        this.questions = response
        this.isValidResponse = true
      },
      error => {
        this.handleErrorForShowallQuestions(error)
      }
    )
  }
  
  handleErrorForShowallQuestions(error: any) {
    this.postAnswerSuccessful = false
    this.isValidResponse = false
  }

  handleShowAnswers(questionid: number) {
    this.postAnswerSuccessful = false
    this.answers = []
    this.isAnswersPosted = true
    this.invalidAnswer = ''
    this.userAnswer = ''

    this.answerService.getAnswerByQuestionId(questionid).subscribe(
      response => {
        this.answers = response
        this.isValidResponse = true
      },
      error => {
        this.isAnswersPosted = false
      }
    )
  }

  handlePostAnswer(questionid : number) {

    if (this.userAnswer && !this.userAnswer.trim()) {
      this.invalidAnswer = 'Answer should not be empty'
      return
    }

      this.userPostedAnswer = new Answer(0, this.userAnswer, questionid, 0, '', '')

      // console.log(questionid, this.userAnswer)

    this.answerService.postAnswer(this.userPostedAnswer).subscribe(
      response => {
        this.postAnswerSuccessful = true
      },
      error => {
        this.invalidAnswer = 'Enter an answer between 6 to 300 characters'
      }
    )

  }

  handlePostQuestion() {
    this.router.navigate(['qaportal/post-question'])
    this.message = 'Question Posted Successfully'
  }

  backToHomePage() {
    this.router.navigate(['/home'])
  }

}
