import { SafeResourceUrl } from "@angular/platform-browser";
import { Category } from "../category/category.model";
import { Question } from "../questions/question.model";
import { Student } from "../student-registered/student.model";

export interface Course {
    id?: number;
    teacherId?: number;
    name?: string;
    createdTime?: number;
    description?: string;
    imgCover?: string;
    level?: number;
    status?: boolean; // public or private
    subscriberNumber?: number;

    // get list course ve se chua list category
    categories?: Category[]; 
    sections?: Section[];
    questions?: [];

    // mark course is new or hot
    isNew?: boolean;
    isHot?: boolean;

    // mark dang hoc or da hoan thanh
    isCompleted?: boolean;

    // so bai hoc da hoc
    passed?: number;
}
export interface CourseRequest {
    categoryIds?: number[];
    createdTime?: number;
    description?: string;
    id?: number;
    teacherId?: number;
    imgCover?: string;
    level?: number;
    name?: string;
    sections?: Section[];
    status?: boolean; // public or private
}

export interface Section {
    courseId?: number;
    id?: number;
    name?: string;
    createdTime?: number;
    lessons: Lesson[];

    // attribute for collapse
    isCollapsed?: boolean;
}

export interface Lesson {
    name?: string;
    id?: number;
    sectionId?: number;
    createTime?: number;
    type?: string; // video or test
    urlVideo?: string;
    urlSafeResourse?: SafeResourceUrl;
    description?: string;

    // attribute for lesson selected
    isChecked?: boolean;

    // option test
    idTest?: number;
}

export interface ResCourseApi {
    data: Course[];
    message: string;
    responseCode: string;
    timestamp: string;
}

export interface ResPaginatorCoursesLearning {
    data: {
        contents: Course[],
        hasNext: boolean,
        totalElements: number,
        totalPages: number
    };
    message: string;
    responseCode: string;
    timestamp: string;
}
export interface ResPaginatorCoursesLeared {
    data: {
        contents: CoursePass[],
        hasNext: boolean,
        totalElements: number,
        totalPages: number
    };
    message: string;
    responseCode: string;
    timestamp: string;
}

export interface CoursePass {
    courseId: number;
    coursePassed: boolean;
    id: number;
    lessonPassed: number[];
}