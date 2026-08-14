package com.sasi.api_testing_platform.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "api_test_history")
public class ApiTestHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String method;

    private Integer statusCode;

    @Column(columnDefinition = "TEXT")
    private String response;

    private Long executionTime;

    private LocalDateTime executedAt;

    public ApiTestHistory() {
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Long executionTime) {
        this.executionTime = executionTime;
    }

    public LocalDateTime getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(LocalDateTime executedAt) {
        this.executedAt = executedAt;
    }
}