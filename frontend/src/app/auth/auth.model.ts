export interface LoginResponse {
    responseCode: string;
    message: string;
    data: {
        refreshToken: string;
        accessToken: string;
        tokenType: string;
        email: string;
        username: string;
        phone: string;
        role: string;
        courseEnrolls: CourseEnroll[];
    }
}
export interface CourseEnroll {
    courseId: number;
    id: number;
    coursePassed: boolean;
    lessonPassed: number[];
}
export interface SignUpReq {
    email: string;
    fullName: string;
    password: string;
    reEnterPassword: string
}