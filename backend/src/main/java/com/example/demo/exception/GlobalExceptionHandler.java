package com.example.demo.exception;

import com.example.demo.enums.ResponseEnum;
import com.example.demo.model.response.CloudResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public ResponseEntity<Object> handleBusinessException(BizException ex, WebRequest request) {
        LOGGER.error("Business exception code={}, message={}", ex.getCode(), ex.getMessage());
        LOGGER.debug("Business exception: ", ex);
        CloudResponse<Object> response = new CloudResponse<>();
        response.setResponseCode(ex.getCode());
        response.setMessage(ex.getMessage());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidateException(MethodArgumentNotValidException ex, WebRequest request) {
        LOGGER.error("Validate exception message: {}", ex.getMessage());
        LOGGER.debug("Validate exception: ", ex);
        CloudResponse<Object> response = new CloudResponse<>();
        response.setResponseCode(ResponseEnum.INPUT_VALID.getCode());
        StringBuilder message = new StringBuilder();
        for (ObjectError error : ex.getAllErrors()) {
            if (message.equals("")) {
                message.append(error.getDefaultMessage());
            } else {
                message.append(", ").append(error.getDefaultMessage());
            }
        }
        response.setMessage(message.toString().trim());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> handleAuthorizationException(AuthorizationException ex, WebRequest request) {
        LOGGER.error("Authorization Error message={}", ex.getMessage());
        LOGGER.debug("Authorization Error: ", ex);
        CloudResponse<Object> response = new CloudResponse<>();
        response.setResponseCode(ResponseEnum.NOT_AUTHORIZATION.getCode());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

}