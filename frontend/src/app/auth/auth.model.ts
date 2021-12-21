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
    }
}

export interface SignUpReq {
    email: string;
    fullName: string;
    password: string;
    reEnterPassword: string
}