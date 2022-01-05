import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthService } from 'src/app/auth/auth.service';
import { CategoryService } from 'src/app/services/category.service';
import { CourseService } from 'src/app/services/course.service';
import { StatusToast, ToastServiceCodex } from 'src/app/services/toast.service';
import { Category } from '../../category/category.model';
import { Course, CourseRequest, Section } from '../course.model';
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
  tabSections: TabSection[] = [];
  selectedFile!: File;
  pathImg = '';

  constructor(
    private dialogRef: MatDialogRef<DialogInfoCourseComponent>,
    @Inject(MAT_DIALOG_DATA) public data: {courseSelected: Course},
    private categoryService: CategoryService,
    private courseService: CourseService,
    private toastService: ToastServiceCodex,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
    this.createForm();
    this.getListCategory();
  }

  createForm() {
    // create form info
    this.urlImg = this.data.courseSelected.imgCover as string;
    this.pathImg = this.data.courseSelected.imgCover as string;
    this.formInforCourse = new FormGroup({
      'imgCover': new FormControl(this.data.courseSelected.imgCover, Validators.required),
      'nameCourse': new FormControl(this.data.courseSelected.name, Validators.required),
      'idCategory': new FormControl('', Validators.required),
      'level': new FormControl(this.data.courseSelected.level, Validators.required),
      'description': new FormControl(this.data.courseSelected.description),
      'status': new FormControl(this.data.courseSelected.status === true ? 'public' : 'private', Validators.required)
    })
    if (!!this.data.courseSelected.categories) {
      this.formInforCourse.get('idCategory')?.patchValue(this.data.courseSelected.categories[0].id);
    }
    // create content tab section + lesson
    this.data.courseSelected.sections?.forEach((section, index) => {
      const tabSection: TabSection = {
        title: `Phần ${index + 1}`,
        removable: index === 0 ? false : true,
        disabled: false,
        active: index === 0 ? true : false,
        nameSection: section.name, 
        lessons: [],
        idSec: section.id
      }

      section.lessons?.forEach((lesson, index) => {
        const tabLesson: TabLesson = {
          title: `Bài ${index + 1}`,
          removable: index === 0 ? false : true,
          disabled: false,
          active: index === 0 ? true : false,
          nameLesson: lesson.name, 
          urlVideo: lesson.urlVideo,
          description: lesson.description,
          idLess: lesson.id
        }
        tabSection.lessons.push(tabLesson);
      })

      this.tabSections.push(tabSection);
    })
  }

  async getListCategory() {
    this.listCategory = await this.categoryService.getListCategory().toPromise();
  }

  updateCourse() {
    const courseReq: CourseRequest = {
      categoryIds: [this.formInforCourse.value.idCategory],
      description: this.formInforCourse.value.description,
      id: this.data.courseSelected.id,
      imgCover: this.pathImg,
      level: this.formInforCourse.value.level,
      name: this.formInforCourse.value.nameCourse,
    }
    // get teacher id
    this.authService.userInfoSubject.subscribe(resData => {
      courseReq.teacherId = resData.id;
    })
    // doi type status: true = public, false = private
    if (this.formInforCourse.value.status === 'public') {
      courseReq.status = true;
    } else {
      courseReq.status = false;
    }

    // update sections + lessons
    const sections: Section[] = [];

    this.tabSections.forEach(tabSec => {
      const sectionReq: Section = {
        courseId: this.data.courseSelected.id,
        id: tabSec.idSec,
        name: tabSec.nameSection,
        lessons: tabSec.lessons.map(tabLess => {
          return {
            name: tabLess.nameLesson,
            id: tabLess.idLess,
            sectionId: tabSec.idSec,
            type: this.radioModel, 
            urlVideo: tabLess.urlVideo,
            description: tabLess.description,
          }
        }),
      };

      if (tabSec.nameSection !== '') {
        sectionReq.lessons = sectionReq.lessons?.filter(less => less.name !== '');
        sections.push(sectionReq);
      }
    })

    courseReq.sections = sections;

    this.courseService.updateCourse(courseReq).subscribe(resData => {
      this.dialogRef.close(
        {courseUpdate: courseReq}
      );
      this.toastService.showToast('Cập nhật khoá học thành công!', StatusToast.SUCCESS);
    }, error => {
      console.log('error update course', error);
      this.toastService.showToast('Cập nhật khoá học thất bại!', StatusToast.ERROR);
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

  openCategory() {
    window.open('/home/category?openDialog=true', '_blank');
  }
  onCancel() {
    this.dialogRef.close();
  }

}
