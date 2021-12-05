import { Course } from "../courses/course.model";

export interface Category {
    stt?: number;
    idCategory?: string;
    nameCategory?: string;
    discription?: string;
    listCourse: Course[];
}