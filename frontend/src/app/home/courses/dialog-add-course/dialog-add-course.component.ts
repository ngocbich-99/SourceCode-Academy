import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import * as moment from 'moment';
import { AuthService } from 'src/app/auth/auth.service';
import { CategoryService } from 'src/app/services/category.service';
import { CourseService } from 'src/app/services/course.service';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { Category } from '../../category/category.model';
import { CourseRequest, Lesson, Section } from '../course.model';

export interface TabSection {
  title: string;
  removable: boolean;
  disabled: boolean;
  active?: boolean;
  customClass?: string;
  nameSection?: string; 
  lessons: TabLesson[];
  idSec?: number;
}
export interface TabLesson {
  title: string;
  removable: boolean;
  disabled: boolean;
  active?: boolean;
  customClass?: string;
  nameLesson?: string; 
  urlVideo?: string;
  description?: string;
  idLess?: number;
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

  listCategory: Category[] = [];

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

  btnInfo: any;
  btnContent: any;
  tabInfo = true;

  radioModel = 'Video';
  formInforCourse: FormGroup = new FormGroup({});
  urlImg = '';
  pathImg = '';
  selectedFile!: File;
  
  constructor(
    public dialogRef: MatDialogRef<DialogAddCourseComponent>,
    private categoryService: CategoryService,
    private courseService: CourseService,
    private toastCodexService: ToastServiceCodex,
    private authService: AuthService
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
      'imgCover': new FormControl('', Validators.required),
      'nameCourse': new FormControl('', Validators.required),
      'idCategory': new FormControl('', Validators.required),
      'level': new FormControl('', Validators.required),
      'description': new FormControl(''),
      'status': new FormControl('', Validators.required)
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

  createCourse() {
    // truyen info course o form vao
    const courseReq: CourseRequest = {
      categoryIds: [this.formInforCourse.value.idCategory],
      createdTime: moment().valueOf(),
      description: this.formInforCourse.value.description,
      imgCover: this.pathImg,
      level: this.formInforCourse.value.level,
      name: this.formInforCourse.value.nameCourse,
    }
    // get teacher id
    this.authService.userInfoSubject.subscribe(resData => {
      courseReq.teacherId = resData.id;
      console.log(resData);
    })
    
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
        name: section.nameSection,
        createdTime: moment().valueOf(),
        lessons: section.lessons.map(lesson => {
          return {
            name: lesson.nameLesson,
            type: this.radioModel,
            urlVideo: lesson.urlVideo,
            createTime: moment().valueOf(),
          }
        })
      };
      if (section.nameSection !== '') {
        sectionReq.lessons = sectionReq.lessons?.filter(lesson => lesson.name !== '');
        sectionList.push(sectionReq);
      }
    });

    courseReq.sections = sectionList;
    
    this.courseService.createCourse(courseReq).subscribe(resData => {
      console.log('response add course', resData);
      courseReq.id = resData.id;
      this.dialogRef.close(
        {data: courseReq}
      );
      this.toastCodexService.showToast('Tạo mới khoá học thành công!', StatusToast.SUCCESS);
    }, error => {
      console.log('error add course', error);
      this.toastCodexService.showToast('Tạo mới khoá học thất bại!', StatusToast.ERROR);
    });
  }

  // function upload image cover
  onSelectImg(event: any) {
    if (event.target.files) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (event: any) => {
        this.urlImg = event.target.result;
      }
      this.selectedFile = <File>event.target.files[0];

      // luu file tren server 
      this.onUploadImg();
    }
  }

  onUploadImg() {
    const formData = new FormData();
    formData.append('name',this.selectedFile.name);
    formData.append('file', this.selectedFile,this.selectedFile.name);
    
    this.courseService.uploadFile(formData).subscribe(resData => {
      console.log('onUploadImg', formData, resData);
      this.pathImg = resData.data;
    })
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
      removable: true,
      nameLesson: ''
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
