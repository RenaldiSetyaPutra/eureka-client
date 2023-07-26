package com.example.eurekaclient.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST, value = HttpStatus.BAD_REQUEST)
public class ResponseErrorException extends RuntimeException implements Serializable {
    private final String code;
    private final HttpStatus httpCode;

    public ResponseErrorException() {
        super(HttpStatus.BAD_REQUEST.getReasonPhrase());
        this.code = HttpStatus.BAD_REQUEST.value() + "any" + "${code.success}";
        this.httpCode = HttpStatus.BAD_REQUEST;
    }

    public ResponseErrorException(String message) {
        super(message);
        this.httpCode = HttpStatus.BAD_REQUEST;
        this.code = HttpStatus.BAD_REQUEST.value() + "any" + "${code.success}";
    }

    public ResponseErrorException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpCode = httpStatus;
    }}