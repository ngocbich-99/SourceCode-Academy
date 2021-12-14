import { Category } from "../category/category.mode";

export interface Question {
    stt?: number;
    id?: number;
    idCourse?: number;
    category?: Category;
    content?: string;
    ans1?: string;
    ans2?: string;
    ans3?: string;
    ans4?: string;
    correctAns?: number;
    createdTime?: number;
}