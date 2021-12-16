import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from '../../category/category.mode';

interface TabSection {
  title: string;
  content: any;
  removable: boolean;
  disabled: boolean;
  active?: boolean;
  customClass?: string;
  nameSection?: string; 
  lessons: TabLesson[];
}
interface TabLesson {
  title: string;
  content: any;
  removable: boolean;
  disabled: boolean;
  active?: boolean;
  customClass?: string;
  nameLesson?: string; 
  urlVideo?: string;
  description?: string;
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

  listLevel: {name: string, id: number}[] = [
    {
      name: 'Cơ bản',
      id: 1
    },
    {
      name: 'Nâng cao',
      id: 2
    },
  ];

  tabSections: TabSection[] = [
    { 
      title: 'Phần 1',
      content: 'Phần 1 content', 
      removable: false, 
      disabled: false,
      active: true,
      nameSection: '',
      lessons: [
        { 
          title: 'Bài 1',
          content: `Bai 1 content`, 
          removable: false, 
          disabled: false,
          active: true,
          nameLesson: '',
          urlVideo: '',
          description: ''
        },
        { 
          title: 'Bài 2',
          content: `Bai 2 content`, 
          removable: true, 
          disabled: false,
          nameLesson: '',
          urlVideo: '',
          description: ''
        },
      ]
    },
    { 
      title: 'Phần 2',
      content: 'Phần 2 content', 
      removable: true, 
      disabled: false,
      nameSection: '', 
      lessons: [
        { 
          title: 'Bài 1',
          content: `Bai 1 content`, 
          removable: false, 
          disabled: false,
          active: true,
          nameLesson: '',
          urlVideo: '',
          description: ''
        },
        { 
          title: 'Bài 2',
          content: `Bai 2 content`, 
          removable: true, 
          disabled: false,
          nameLesson: '',
          urlVideo: '',
          description: ''
        },
      ]
    },
  ];

  btnInfo: any;
  btnContent: any;
  tabInfo = true;

  radioModel = 'Video';

  listCategory: Category[] = [];
  formInforCourse: FormGroup = new FormGroup({});
  formContentCourse: FormGroup = new FormGroup({});

  urlImg = '';

  constructor(
    public dialogRef: MatDialogRef<DialogAddCourseComponent>,
    private categoryService: CategoryService
  ) { }

  ngOnInit(): void {
    this.btnInfo = document.getElementById('btn-info');
    this.btnContent = document.getElementById('btn-content');

    this.createForm();
    this.getListCategory();
  }

  async getListCategory() {
    this.listCategory = await this.categoryService.getListCategory().toPromise();
  }

  createForm() {
    this.formInforCourse = new FormGroup({
      'imgPath': new FormControl('', Validators.required),
      'nameCourse': new FormControl('', Validators.required),
      'idCategory': new FormControl('', Validators.required),
      'level': new FormControl('', Validators.required),
      'description': new FormControl(''),
      'status': new FormControl('', Validators.required)
    })
    this.formContentCourse = new FormGroup({
      'nameSection': new FormControl('', Validators.required),
      'nameLesson': new FormControl('', Validators.required)
    })
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

    // active section 1 lession 1
    this.tabSections[0].active = true;
    this.tabSections[0].lessons[0].active = true;
  }
  
  addTabSection(): void {
    const newTabIndex = this.tabSections.length + 1;
    this.tabSections.push({
      title: `Phần ${newTabIndex}`,
      content: `Dynamic content ${newTabIndex}`,
      disabled: false,
      removable: true,
      nameSection: '',
      lessons: [
        { 
          title: 'Bài 1',
          content: `Bai 1 content`, 
          removable: false, 
          disabled: false,
          active: true
        },
      ]
    });
  }

  addTabLesson(tabSection: TabSection) {
    const newTabIndex = tabSection.lessons.length + 1;
    tabSection.lessons.push({
      title: `Bài ${newTabIndex}`,
      content: `Dynamic content ${newTabIndex}`,
      disabled: false,
      removable: true,
      nameLesson: ''
    });
  }

  removeTabSection(tab: TabSection): void {
    this.tabSections.splice(this.tabSections.indexOf(tab), 1);
    console.log('Remove Tab section');
  }

  removeTabLesson(tabLesson: TabLesson, tabSec: TabSection) {
    tabSec.lessons.splice(tabSec.lessons.indexOf(tabLesson), 1);
    console.log('Remove Tab lesson');
  }

  onCancel() {
    this.dialogRef.close();
  }

  createCourse() {
    this.dialogRef.close();
    console.log(this.formInforCourse);
    console.log(this.tabSections);
  }


  // function upload image cover
  onSelectImg(event: any) {
    if (event.target.files) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (event: any) => {
        console.log('event onload reader', event);
        
        this.urlImg = event.target.result;
      }
    }
  }
}
