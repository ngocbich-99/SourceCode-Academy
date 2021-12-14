import { Question } from "../questions/question.model";
import { Student } from "../student-registered/student.model";

export interface Course {
    idCourse?: number;
    idTeacher?: number;
    nameCourse?: string;
    questionList?: Question[];
    sectionSet?: Section[];
    studentSet?: Student[];
    createdTime?: number;
    description?: string;
    imgCover?: string;
    level?: number;
    status?: boolean; // public or private
}
export interface Section {
    idSection?: number;
    nameSection?: string;
    createdTime?: number;
    listLesson?: Lesson[];
}

export interface Lesson {
    idLesson?: number;
    createdTime?: number;
    type?: string; 
    urlVideo?: string;
    idTest?: number;
    description?: string;
}