package com.sasi.api_testing_platform.service;

import com.sasi.api_testing_platform.dto.ApiRequest;
import com.sasi.api_testing_platform.entity.ApiTestHistory;
import com.sasi.api_testing_platform.repository.ApiTestHistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Service
public class ApiExecutionService {

    private final RestClient restClient;
    private final ApiTestHistoryRepository historyRepository;

    public ApiExecutionService(
            RestClient.Builder builder,
            ApiTestHistoryRepository historyRepository) {

        this.restClient = builder.build();
        this.historyRepository = historyRepository;
    }

    // GET API
    public String executeGet(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        ResponseEntity<String> response = restClient
                .get()
                .uri(request.getUrl())
                .retrieve()
                .toEntity(String.class);

        long executionTime =
                System.currentTimeMillis() - startTime;

        saveHistory(
                request,
                response.getStatusCode().value(),
                response.getBody(),
                executionTime
        );

        return response.getBody();
    }

    // POST API
    public String executePost(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        ResponseEntity<String> response = restClient
                .post()
                .uri(request.getUrl())
                .header("Content-Type", "application/json")
                .body(request.getBody())
                .retrieve()
                .toEntity(String.class);

        long executionTime =
                System.currentTimeMillis() - startTime;

        saveHistory(
                request,
                response.getStatusCode().value(),
                response.getBody(),
                executionTime
        );

        return response.getBody();
    }

    // PUT API
    public String executePut(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        ResponseEntity<String> response = restClient
                .put()
                .uri(request.getUrl())
                .header("Content-Type", "application/json")
                .body(request.getBody())
                .retrieve()
                .toEntity(String.class);

        long executionTime =
                System.currentTimeMillis() - startTime;

        saveHistory(
                request,
                response.getStatusCode().value(),
                response.getBody(),
                executionTime
        );

        return response.getBody();
    }

    // DELETE API
    public String executeDelete(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        ResponseEntity<String> response = restClient
                .delete()
                .uri(request.getUrl())
                .retrieve()
                .toEntity(String.class);

        long executionTime =
                System.currentTimeMillis() - startTime;

        saveHistory(
                request,
                response.getStatusCode().value(),
                response.getBody(),
                executionTime
        );

        return response.getBody();
    }

    // PATCH API
    public String executePatch(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        ResponseEntity<String> response = restClient
                .patch()
                .uri(request.getUrl())
                .header("Content-Type", "application/json")
                .body(request.getBody())
                .retrieve()
                .toEntity(String.class);

        long executionTime =
                System.currentTimeMillis() - startTime;

        saveHistory(
                request,
                response.getStatusCode().value(),
                response.getBody(),
                executionTime
        );

        return response.getBody();
    }

    // Save API execution history
    private void saveHistory(
            ApiRequest request,
            int statusCode,
            String responseBody,
            long executionTime) {

        ApiTestHistory history = new ApiTestHistory();

        history.setUrl(request.getUrl());
        history.setMethod(request.getMethod());
        history.setStatusCode(statusCode);
        history.setResponse(responseBody);
        history.setExecutionTime(executionTime);
        history.setExecutedAt(LocalDateTime.now());

        historyRepository.save(history);
    }
}