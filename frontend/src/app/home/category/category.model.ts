import { Course } from "../courses/course.model";

export interface Category {
    stt?: number;
    id?: number;
    name?: string;
    description?: string;
    createdTime?: number;
    courseCount?: number;
    courses?: Course[];
}