export interface Account {
    stt?: number;
    idAccount?: number;
    userName?: string;
    email?: string;
    phone?: string;
    role?: string;
    isActivate?: boolean;
    createdTime?: number; //doi ve dang millisecond trong db
    password?: string;
}