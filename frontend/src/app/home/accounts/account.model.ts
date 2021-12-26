export interface Account {
    stt?: number;
    id?: number;
    fullName?: string;
    username?: string;
    email?: string;
    phone?: string;
    role?: string;
    isActivate?: boolean;
    createdTime?: number; //doi ve dang millisecond trong db
    password?: string;
}

export interface ResponseAccount {
    data: Account;
    message: string;
    responseCode: string;
    timestamp: string;
}