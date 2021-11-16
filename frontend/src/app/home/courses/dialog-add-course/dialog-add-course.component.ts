import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-dialog-add-course',
  templateUrl: './dialog-add-course.component.html',
  styleUrls: ['./dialog-add-course.component.css']
})
export class DialogAddCourseComponent implements OnInit {
  listStatus: {key: string, value: string}[] = [
    {
      key: 'public',
      value: 'Công khai'
    },
    {
      key: 'private',
      value: 'Chưa công khai'
    }
  ];
  selectedStatus = this.listStatus[0].key;

  listCategory: {name: string, id: string}[] = [
    {
      name: 'HTML',
      id: '1'
    },
    {
      name: 'JS',
      id: '2'
    },
    {
      name: 'Python',
      id: '3'
    },
  ];
  selectedCategory = this.listCategory[0].id;

  nameCourseFormControl = new FormControl('', [Validators.required]);
  constructor() { }

  ngOnInit(): void {
  }

}
