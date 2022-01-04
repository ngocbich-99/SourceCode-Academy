package com.example.demo.model.response;

import com.example.demo.enums.ResponseEnum;
import lombok.Data;
import org.springframework.core.io.Resource;

import java.io.Serializable;
import java.util.Date;

@Data
public class CloudFileResponse<T> implements Serializable {

    private String responseCode;

    private String message;

    private Date timestamp = new Date();

    private T data;

    private Resource resource;

    private static <T> CloudFileResponse<T> build() {
        return new CloudFileResponse<>();
    }

    public static <T> CloudFileResponse<T> ok(T data, Resource resource) {
        CloudFileResponse<T> response = build();
        response.setResponseCode(ResponseEnum.SUCCESS.getCode());
        response.setMessage(ResponseEnum.SUCCESS.getMessage());
        response.setData(data);
        response.setResource(resource);
        return response;
    }

    public static <T> CloudFileResponse<T> ok(T data, Resource resource, String message) {
        CloudFileResponse<T> response = ok(data, resource);
        response.setMessage(message);
        return response;
    }
}
