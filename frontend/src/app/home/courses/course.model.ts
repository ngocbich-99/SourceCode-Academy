import { Category } from "../category/category.mode";
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
}

export interface CourseRequest {
    categoryIds?: number[];
    createdTime?: number;
    description?: string;
    idCourse?: number;
    teacherId?: number;
    imgCover?: string;
    level?: number;
    name?: string;
    sections?: Section[];
    status?: boolean; // public or private
}

export interface CourseResponse {
    idCourse?: number;
    idTeacher?: number;
    category?: Category[]; 
    nameCourse?: string;
    createdTime?: number;
    status?: boolean; // public or private
    level?: number;
    imgCover?: string;
    description?: string;
    sectionList?: Section[];
}
export interface Section {
    courseId?: number;
    id?: number;
    name?: string;
    createdTime?: number;
    lessons?: Lesson[];
}

export interface Lesson {
    idLesson?: number;
    name?: string;
    id?: number;
    createdTime?: number;
    type?: string; // video or test
    urlVideo?: string;
    idTest?: number;
    description?: string;
}