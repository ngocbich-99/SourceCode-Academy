package com.example.demo.exception;

import com.example.demo.enums.ResponseEnum;

public class BizException extends RuntimeException {

    private final String code;

    public BizException(ResponseEnum responseEnum, String message) {
        super(message);
        this.code = responseEnum.getCode();
    }

    public BizException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.code = responseEnum.getCode();
    }

    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}