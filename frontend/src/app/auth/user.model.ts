export interface User {
    refreshToken?: string;
    accessToken?: string;
    tokenType?: string;
    email?: string;
    fullName?: string;
    username?: string;
    phone?: string;
    role?: string;
}

export interface UserInfo {
    id?: number;
    email?: string,
    username?: string,
    fullName?: string,
    phone?: null,
    role?: string,
    createdTime?: number,
    isActivate?: boolean
}