import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CategoryService } from 'src/app/services/category.service';
import { Category } from '../../category/category.model';
import { Course } from '../course.model';
import { TabLesson, TabSection } from '../dialog-add-course/dialog-add-course.component';

@Component({
  selector: 'app-dialog-info-course',
  templateUrl: './dialog-info-course.component.html',
  styleUrls: ['./dialog-info-course.component.css']
})
export class DialogInfoCourseComponent implements OnInit {
  formInforCourse: FormGroup = new FormGroup({});
  urlImg = '';
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
  radioModel = 'Video';
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

  constructor(
    private dialogRef: MatDialogRef<DialogInfoCourseComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {courseSelected: Course},
    private categoryService: CategoryService,
  ) { }

  ngOnInit(): void {
    this.createForm();
    this.getListCategory();
  }

  createForm() {
    this.formInforCourse = new FormGroup({
      'imgCover': new FormControl('', Validators.required),
      'nameCourse': new FormControl(this.data.courseSelected.name, Validators.required),
      'idCategory': new FormControl('', Validators.required),
      'level': new FormControl(this.data.courseSelected.level, Validators.required),
      'description': new FormControl(this.data.courseSelected.description),
      'status': new FormControl(this.data.courseSelected.status === true ? 'public' : 'private', Validators.required)
    })
    if (!!this.data.courseSelected.categories) {
      this.formInforCourse.get('idCategory')?.patchValue(this.data.courseSelected.categories[0].id);
    }
  }

  async getListCategory() {
    this.listCategory = await this.categoryService.getListCategory().toPromise();
  }

  updateCourse() {

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
  // function upload image cover
  onSelectImg(event: any) {
    if (event.target.files) {
      var reader = new FileReader();
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = (event: any) => {
        this.urlImg = event.target.result;
        console.log(this.urlImg);
      }
    }
  }
  openCategory() {
    window.open('/home/category?openDialog=true', '_blank');
  }
  onCancel() {
    this.dialogRef.close();
  }

}
