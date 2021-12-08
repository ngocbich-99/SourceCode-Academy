import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Question } from './question.model';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.css']
})
export class QuestionsComponent implements OnInit {
  dataSource = new MatTableDataSource<Question>();
  displayedColumns = [
    'stt',
    'category',
    'course',
    'content',
    'action'
  ];
  constructor() { }

  ngOnInit(): void {
  }
  addQuestion() {
    
  }
  updateQuestion(ques: Question) {

  }
  deleteQuestion(ques: Question) {

  }

}
