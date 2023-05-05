package com.kristianandi.BackendTest.Core.Domain;

public class ApiResponse {

    public ApiResponse()
    {

    }
    public ApiResponse(int statusCode, String message)
    {
        this.statusCode = statusCode;
        this.message = message;
    }
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int statusCode;
    public String message;
    public Object data;
}
