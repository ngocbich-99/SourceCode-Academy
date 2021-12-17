import { Category } from "../category/category.mode";
import { Question } from "../questions/question.model";
import { Student } from "../student-registered/student.model";

export interface Course {
    idCourse?: number;
    idTeacher?: number;
    nameCourse?: string;
    sectionList?: Section[];
    createdTime?: number;
    description?: string;
    imgCover?: string;
    level?: number;
    status?: boolean; // public or private
    categoryIds?: string[];

    // get list course ve se chua list category
    category?: Category[]; 
    questionList?: Question[];
    studentSet?: Student[];
}

export interface CourseRequest {
    categoryIds?: string[];
    createdTime?: number;
    description?: string;
    idCourse?: number;
    idTeacher?: number;
    imgCover?: string;
    level?: number;
    nameCourse?: string;
    sectionList?: Section[];
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
    idSection?: number;
    sectionName?: string;
    createdTime?: number;
    listLesson?: Lesson[];
}

export interface Lesson {
    idLesson?: number;
    nameLesson?: string;
    idSection?: number;
    createdTime?: number;
    type?: string; // video or test
    urlVideo?: string;
    idTest?: number;
    description?: string;
}

export interface ContentLesson {
    type?: string; // video or test
    urlVideo?: string;
    idTest?: string;
}