package com.example.demo.model.response;

import com.example.demo.enums.ResponseEnum;
import lombok.Data;

import java.util.Date;

@Data
public class CloudResponse<T> {

    private String responseCode;

    private String message;

    private Date timestamp = new Date();

    private T data;

    private static <T> CloudResponse<T> build() {
        return new CloudResponse<>();
    }

    public static <T> CloudResponse<T> ok(T data) {
        CloudResponse<T> response = build();
        response.setResponseCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    public static <T> CloudResponse<T> ok(T data, String message) {
        CloudResponse<T> response = ok(data);
        response.setMessage(message);
        return response;
    }

    public static <T> CloudResponse<T> failed(String code, T data, String message) {
        CloudResponse<T> response = build();
        response.setResponseCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }

}