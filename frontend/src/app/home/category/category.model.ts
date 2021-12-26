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

// respose tra ve khi get courses by category
export interface ResCourses {
    data: Course[];
    responseCode: string;
    message: string;
    timestamp: string;
}