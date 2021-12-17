import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSelect } from '@angular/material/select';
import { MatTableDataSource } from '@angular/material/table';
import * as moment from 'moment';
import { QuestionService } from 'src/app/services/question.service';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
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
        {idCourse: 0, nameCourse: 'Bulbasaur'},
        {idCourse: 1, nameCourse: 'Bulbasaur'},
        {idCourse: 2, nameCourse: 'Bulbasaur'},
      ],
    },
    {
      nameCategory: 'Water',
      course: [
        {idCourse: 0, nameCourse: 'Bulbasaur'},
        {idCourse: 1, nameCourse: 'Bulbasaur'},
        {idCourse: 2, nameCourse: 'Bulbasaur'},
      ],
    },
    {
      nameCategory: 'Fire',
      disabled: true,
      course: [
        {idCourse: 0, nameCourse: 'Bulbasaur'},
        {idCourse: 1, nameCourse: 'Bulbasaur'},
        {idCourse: 2, nameCourse: 'Bulbasaur'},
      ],
    },
    {
      nameCategory: 'Psychic',
      course: [
        {idCourse: 0, nameCourse: 'Bulbasaur'},
        {idCourse: 1, nameCourse: 'Bulbasaur'},
      ],
    },
  ];

  listQuestion: Question[] = [];

  constructor(
    public dialog: MatDialog,
    private questionService: QuestionService,
    private toastService: ToastServiceCodex,

  ) { }

  ngOnInit(): void {
    //this.getListQuestion();
  }

  getListQuestion() {
    this.questionService.getListQuestion().subscribe(resData => {
      console.log(resData);
      this.listQuestion = resData;
      this.dataSource.data = this.listQuestion;
    })
  }
  addQuestion() {
    const dialogRef = this.dialog.open(DialogAddQuestionComponent, {
      width: '800px',
      height: '716px',
    });
    dialogRef.afterClosed().subscribe(rs => {
      if (!!rs) {
        // todo logic
        console.log('form create question value',rs);
        const questionReq: Question = {
          idCourse: rs.idCourse,
          content: rs.content,
          ans1: rs.ans1,
          ans2: rs.ans2,
          ans3: rs.ans3,
          ans4: rs.ans4,
          correctAns: rs.correctAns,
          createdTime: moment().valueOf()
        }
        this.questionService.createQuestion(questionReq).subscribe(resData => {
          this.toastService.showToast('Thêm câu hỏi thành công!', StatusToast.SUCCESS);
        }, error => {
          console.log(error);
          this.toastService.showToast('Thêm câu hỏi thất bại!', StatusToast.ERROR);
        })
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
