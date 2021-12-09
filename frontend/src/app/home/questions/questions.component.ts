import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSelect } from '@angular/material/select';
import { MatTableDataSource } from '@angular/material/table';
import { Course } from '../courses/course.model';
import { DialogAddQuestionComponent } from './dialog-add-question/dialog-add-question.component';
import { Question } from './question.model';

interface CategoryGroup {
  disabled?: boolean;
  nameCategory: string;
  course: Course[];
}
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

  categoryControl = new FormControl();
  categoryGroups: CategoryGroup[] = [
    {
      nameCategory: 'Grass',
      course: [
        {id: 0, name: 'Bulbasaur'},
        {id: 1, name: 'Bulbasaur'},
        {id: 2, name: 'Bulbasaur'},
      ],
    },
    {
      nameCategory: 'Water',
      course: [
        {id: 0, name: 'Bulbasaur'},
        {id: 1, name: 'Bulbasaur'},
        {id: 2, name: 'Bulbasaur'},
      ],
    },
    {
      nameCategory: 'Fire',
      disabled: true,
      course: [
        {id: 0, name: 'Bulbasaur'},
        {id: 1, name: 'Bulbasaur'},
        {id: 2, name: 'Bulbasaur'},
      ],
    },
    {
      nameCategory: 'Psychic',
      course: [
        {id: 0, name: 'Bulbasaur'},
        {id: 1, name: 'Bulbasaur'},
      ],
    },
  ];
  constructor(
    public dialog: MatDialog,
  ) { }

  ngOnInit(): void {
  }
  addQuestion() {
    const dialogRef = this.dialog.open(DialogAddQuestionComponent, {
      width: '800px',
      height: '716px',
    });
    dialogRef.afterClosed().subscribe(rs => {
      if (!!rs) {
        // todo logic
        console.log(rs);
      }
    })
  }
  updateQuestion(ques: Question) {

  }
  deleteQuestion(ques: Question) {

  }

  filterCourse(event: any) {
    // value is id of course
    console.log(event.value);
  }

}
