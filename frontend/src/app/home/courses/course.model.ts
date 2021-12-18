import { Category } from "../category/category.mode";
import { Question } from "../questions/question.model";
import { Student } from "../student-registered/student.model";

export interface Course {
    idCourse?: number;
    idTeacher?: number;
    nameCourse?: string;
    sections?: Section[];
    createdTime?: number;
    description?: string;
    imgCover?: string;
    level?: number;
    status?: boolean; // public or private
    categoryIds?: number[];

    // get list course ve se chua list category
    category?: Category[]; 
    questionList?: Question[];
    studentSet?: Student[];
}

export interface CourseRequest {
    categoryIds?: number[];
    createdTime?: number;
    description?: string;
    idCourse?: number;
    idTeacher?: number;
    imgCover?: string;
    level?: number;
    nameCourse?: string;
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
    idSection?: number;
    sectionName?: string;
    createdTime?: number;
    lessons?: Lesson[];
}

export interface Lesson {
    idLesson?: number;
    lessonName?: string;
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
    idTest?: number;
    description?: string;
}