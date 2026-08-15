package com.sasi.api_testing_platform.dto;

public class ApiExecutionResponse {

    private int statusCode;
    private String response;
    private long executionTime;

    public ApiExecutionResponse() {
    }

    public ApiExecutionResponse(
            int statusCode,
            String response,
            long executionTime) {

        this.statusCode = statusCode;
        this.response = response;
        this.executionTime = executionTime;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}