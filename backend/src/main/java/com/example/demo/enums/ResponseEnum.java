package com.example.demo.enums;

public enum ResponseEnum {
    /**
     * <p> 00000 Success </p>
     * <p> 1xxxx Error input data </p>
     * <p> 2xxxx Error business </p>
     * <p> 3xxxx Error SAP </p>
     * <p> 4xxxx Error CMS </p>
     * <p> 5xxxx Error Common service OTP </p>
     * <p> 6xxxx Error Firebase user device </p>
     */
    SUCCESS("00000", "Successful"),
    NEED_MORE_VERIFICATION("00001", "Có thể đầu vào được xác thực, nhưng cần thêm 1 vài bước để tiếp tục"),

    //Error login
    LOGIN_FAIL("20000", "Login fail"),
    USERNAME_NOT_EXIST("20002", "Username does not exist"),
    LOGIN_INCORRECT_PASSWORD("20003", "Incorrect password"),
    LOGIN_INCORRECT_EXCEEDED("20004",
                                     "Your account has been temporarily suspended because you have exceeded the maximum number of incorrect attempts. Please contact LAMB hotline for assistance"),
    ACCOUNT_BLOCKED("20005",
                            "Your account has been blocked. Please contact LAMB hotline for assistance"),
    ACCOUNT_TEMPORARILY_BLOCKED("20006",
                                        "Your account has been temporarily suspended. Please try again later "),
    ACCOUNT_BD_NUM_NOT_EXIST("20007", "Business Partner number does not exist"),
    ACCOUNT_USERNAME_EXISTED("20008", "Username already exists"),
    ACCOUNT_USERNAME_WRONG_FORMAT("20009", "Username wrong format"),
    NOT_FOUND("20010", "Not found"),
    COURSE_ALREADY_IN_ACCOUNT("20011", "Course already in account"),
    ACCOUNT_ALREADY_USERNAME("20012", "Account already has username"),
    OTP_REQUEST_FAIL("20013", "Request OTP fail"),
    OTP_REQUEST_TEMP_BLOCK("20014", "Xác thực OTP tạm khóa trong 24 giờ. Vui lòng thử lại sau"),
    USERNAME_OR_PHONE_NOT_MATCH("20015", "Username does not exist or phone number does not match"),
    NEW_PASS_NOT_SAME_RECENT("20016",
                                     "The new password must not be the same as the current password or the most recent password"),
    OTP_VERIFY_FAIL("20001", "Verify OTP fail"),
    PASSWORD_INVALID("20017", "Password invalid"),
    RESET_PASSWORD_FAIL("20018", "Reset Password Fail"),
    OLD_PASSWORD_INCORRECT("20019", "Old password incorrect"),
    CHANGE_PASSWORD_FAIL("20022", "Change Password Fail"),
    ACCOUNT_IS_NOT_SUPPORT("20020", "Account is not support"),
    PASSWORD_DOSE_NOT_CONTAIN_INFOS("20021", "Password does not contain username, email or phone number of this account within it"),
    ACCOUNT_DOES_NOT_EXISTED("20022", "Account does not exist"),
    VERIFY_CAPTCHA_FAILED("20023","Verify captcha failed"),
    NEED_CHANGE_PASSWORD_FIRST_LOGIN("2024","Need change password first login"),
    PERMISSIONS_DENY("2025","PERMISSIONS DENY"),

    //Error IO
    IO("30000", "Call API from SAP failed"),
    SAP_PAYMENT_CALC_ERROR("30001", "Error get payment calculator from SAP"),


    COMMON_OTP_SERVICE_ERROR("50000", "Error from common OTP service"),

    // Firebase
    USER_DEVICE_NOT_FOUND("60000", "User device not exist"),
    //
    INPUT_VALID("99997", "Input invalid. Please try again"),
    NOT_AUTHORIZATION("99998", "Not authorization"),
    ERROR("99999", "The system is busy, please try again later");

    private final String code;

    private final String message;

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    ResponseEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
