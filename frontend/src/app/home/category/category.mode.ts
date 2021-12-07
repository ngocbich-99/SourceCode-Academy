import { Course } from "../courses/course.model";

export interface Category {
    stt?: number;
    idCategory?: number;
    nameCategory?: string;
    description?: string;
    createdTime?: number;
    courses?: Course[];
}