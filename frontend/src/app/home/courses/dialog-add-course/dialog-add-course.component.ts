import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import * as moment from 'moment';
import { CategoryService } from 'src/app/services/category.service';
import { CourseService } from 'src/app/services/course.service';
import { Category } from '../../category/category.mode';
import { CourseRequest, Lesson, Section } from '../course.model';

interface TabSection {
  title: string;
  removable: boolean;
  disabled: boolean;
  active?: boolean;
  customClass?: string;
  nameSection?: string; 
  lessons: TabLesson[];
}
interface TabLesson {
  title: string;
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
  selectedStatus = this.listStatus[0].key;

  listCategory: Category[] = [];

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

  tabSections: TabSection[] = [
    { 
      title: 'Phần 1',
      removable: false, 
      disabled: false,
      active: true,
      nameSection: '',
      lessons: [
        { 
          title: 'Bài 1',
          removable: false, 
          disabled: false,
          active: true,
          nameLesson: '',
          urlVideo: '',
          description: ''
        },
        { 
          title: 'Bài 2',
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
      removable: true, 
      disabled: false,
      nameSection: '', 
      lessons: [
        { 
          title: 'Bài 1',
          removable: false, 
          disabled: false,
          active: true,
          nameLesson: '',
          urlVideo: '',
          description: ''
        },
        { 
          title: 'Bài 2',
          removable: true, 
          disabled: false,
          nameLesson: '',
          urlVideo: '',
          description: ''
        },
      ]
    },
  ];

  radioModel = 'Video';
  formInforCourse: FormGroup = new FormGroup({});
  formContentCourse: FormGroup = new FormGroup({});
  urlImg = '';
  
  constructor(
    public dialogRef: MatDialogRef<DialogAddCourseComponent>,
    private categoryService: CategoryService,
    private courseService: CourseService
  ) { }

  ngOnInit(): void {
    this.btnInfo = document.getElementById('btn-info');
    this.btnContent = document.getElementById('btn-content');
  }

  async getListCategory() {
    this.listCategory = await this.categoryService.getListCategory().toPromise();
  }

  createForm() {
    this.formInforCourse = new FormGroup({
      'imgCover': new FormControl('', Validators.required),
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
  }

  createCourse() {
    this.dialogRef.close();
    console.log(this.formInforCourse);
    console.log(this.tabSections);

    // truyen info course o form vao
    const courseReq: CourseRequest = {
      categoryIds: [this.formInforCourse.value.idCategory],
      createdTime: moment().valueOf(),
      description: this.formInforCourse.value.description,
      imgCover: this.formInforCourse.value.imgCover,
      level: this.formInforCourse.value.level,
      nameCourse: this.formInforCourse.value.nameCourse,
    }
    // doi type cua status cho phu hop voi body request api
    if (this.formInforCourse.value.status === 'public') {
      courseReq.status = true;
    } else if (this.formInforCourse.value.status === 'private'){
      courseReq.status = false;
    }

    // truyen list section cho course
    const sectionList: Section[] = [];

    this.tabSections.forEach(section => {
      const sectionReq: Section = {
        sectionName: section.nameSection,
        createdTime: moment().valueOf(),
        listLesson: section.lessons.map(lesson => {
          return {
            nameLesson: lesson.nameLesson,
            description: lesson.description,
            urlVideo: lesson.urlVideo,
            createdTime: moment().valueOf(),
          }
        })
      };
      if (section.nameSection !== '') {
        sectionReq.listLesson = sectionReq.listLesson?.filter(lesson => lesson.nameLesson !== '');
        sectionList.push(sectionReq);
      }
    });

    courseReq.sectionList = sectionList;
    this.courseService.createCourse(courseReq).subscribe(resData => {
      console.log(resData);
    });
    // toast tao khoa hoc thanh cong
  }


  // function upload image cover
  onSelectImg(event: any) {
    if (event.target.files) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (event: any) => {
        this.urlImg = event.target.result;
      }
    }
  }

  addTabSection(): void {
    const newTabIndex = this.tabSections.length + 1;
    this.tabSections.push({
      title: `Phần ${newTabIndex}`,
      disabled: false,
      removable: true,
      nameSection: '',
      lessons: [
        { 
          title: 'Bài 1',
          removable: false, 
          disabled: false,
          active: true
        },
      ]
    });
  }

  addTabLesson(tabSec: TabSection) {
    const newTabIndex = tabSec.lessons.length + 1;
    tabSec.lessons.push({
      title: `Bài ${newTabIndex}`,
      disabled: false,
      removable: true
    });
  }

  removeTabSection(tab: TabSection): void {
    this.tabSections.splice(this.tabSections.indexOf(tab), 1);
    console.log('Remove Tab section');
  }

  removeTabLesson(tabLess: TabLesson, tabSec: TabSection) {
    tabSec.lessons.splice(tabSec.lessons.indexOf(tabLess), 1);
    console.log('Remove Tab lesson');
  }

  openCategory() {
    window.open('/home/category?openDialog=true', '_blank');
  }

  onCancel() {
    this.dialogRef.close();
  }
}
