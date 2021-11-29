import { Component, OnInit } from '@angular/core';
import {FormControl, Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

interface ITab {
  title: string;
  content: string;
  removable: boolean;
  disabled: boolean;
  active?: boolean;
  customClass?: string;
}

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

  listLevel: {name: string, id: string}[] = [
    {
      name: 'Cơ bản',
      id: '1'
    },
    {
      name: 'Nâng cao',
      id: '2'
    },
  ];
  selectedLevel = this.listLevel[0].id;

  nameCourseFormControl = new FormControl('', [Validators.required]);

  btnInfo: any;
  btnContent: any;
  tabInfo = true;

  tabSections: ITab[] = [
    { 
      title: 'Phần 2',
      content: `<h3>heading</h3>`, 
      removable: true, 
      disabled: false
    },
    { 
      title: 'Phần 3', 
      content: 'Dynamic content 2', 
      removable: true, 
      disabled: false
    },
  ];

  tabLessons: ITab[] = [
    { 
      title: 'Bài 2',
      content: `<h3>heading</h3>`, 
      removable: true, 
      disabled: false
    },
    { 
      title: 'Bài 3', 
      content: 'Dynamic content 2', 
      removable: true, 
      disabled: false
    },
  ];

  radioModel = 'Video';

  constructor(
    public dialogRef: MatDialogRef<DialogAddCourseComponent>,
  ) { }

  ngOnInit(): void {
    this.btnInfo = document.getElementById('btn-info');
    this.btnContent = document.getElementById('btn-content');
  }

  inforGeneral() {
    this.btnInfo.classList.add('clicked');
    this.btnContent.classList.remove('clicked');
    this.tabInfo = true;
  }
  contentCourse() {
    this.btnContent.classList.add('clicked');
    this.btnInfo.classList.remove('clicked');
    this.tabInfo = false;
  }
  
  addTabSection(): void {
    const newTabIndex = this.tabSections.length + 2;
    this.tabSections.push({
      title: `Phần ${newTabIndex}`,
      content: `Dynamic content ${newTabIndex}`,
      disabled: false,
      removable: true
    });
  }

  addTabLesson() {
    const newTabIndex = this.tabLessons.length + 2;
    this.tabLessons.push({
      title: `Bài ${newTabIndex}`,
      content: `Dynamic content ${newTabIndex}`,
      disabled: false,
      removable: true
    });
  }

  removeTabSection(tab: ITab): void {
    this.tabSections.splice(this.tabSections.indexOf(tab), 1);
    console.log('Remove Tab section');
  }

  removeTabLesson(tab: ITab) {
    this.tabLessons.splice(this.tabLessons.indexOf(tab), 1);
    console.log('Remove Tab lesson');
  }

  onCancel() {
    this.dialogRef.close();
  }

}
