import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Questions } from '../qaportal/qaportal.component';
import { QAService } from '../service/data/qa.service';

@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.css']
})
export class PostQuestionComponent implements OnInit {

  question!: Questions

  isPosted = false

  invalidTitle = ''

  invalidDescription = ''

  invalidDetails = ''

  constructor(
    private qaService : QAService,
    private router: Router
  ) { }

  ngOnInit(): void  {
    this.question = new Questions(0, '', '', 0, '', '')
  } 

  handlePostQuestion() {
    this.invalidTitle= ''
    this.invalidDescription = ''
    this.invalidDetails = ''
    
    if(this.question.title && !this.question.title.trim()) {
      this.invalidTitle = 'Title should not be empty'
      return
    }

    else if(this.question.description && !this.question.description.trim()) {
        this.invalidDescription = 'Description should not be empty'
        return 
      }

    else {
    this.qaService.postQuestion(this.question).subscribe(
      response => {
        this.isPosted = true
      },
      error => {
        this.invalidDetails = 'Enter correct details '
      }
    )
    }
  }

}
