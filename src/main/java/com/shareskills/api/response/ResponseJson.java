package com.shareskills.api.response;

import lombok.Getter;

@Getter
public class ResponseJson<T> {
    private T data;
    private String message;
    private final int statusCode;

    private long exp;

    public ResponseJson(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseJson(T data, int statusCode) {
        this.data = data;
        this.statusCode = statusCode;
    }

    public ResponseJson(T data, int statusCode, long exp) {
        this.data = data;
        this.statusCode = statusCode;
        this.exp = exp;
    }

    public ResponseJson(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

}
