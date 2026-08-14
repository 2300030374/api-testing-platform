package com.sasi.api_testing_platform.service;

import com.sasi.api_testing_platform.dto.ApiRequest;
import com.sasi.api_testing_platform.entity.ApiTestHistory;
import com.sasi.api_testing_platform.repository.ApiTestHistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.client.RestClientResponseException;
import java.time.LocalDateTime;
import java.util.Map;

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

    // Build URL with query parameters
    private String buildUrl(ApiRequest request) {

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(request.getUrl());

        Map<String, String> queryParams = request.getQueryParams();

        if (queryParams != null) {
            queryParams.forEach(builder::queryParam);
        }

        return builder.toUriString();
    }

    // GET API
    public String executeGet(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        try {

            RestClient.RequestHeadersSpec<?> requestSpec =
                    restClient
                            .get()
                            .uri(buildUrl(request));

            addHeaders(requestSpec, request);

            ResponseEntity<String> response =
                    requestSpec
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

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // POST API
    public String executePost(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        try {

            RestClient.RequestBodySpec requestSpec =
                    restClient
                            .post()
                            .uri(buildUrl(request));

            addHeaders(requestSpec, request);

            ResponseEntity<String> response =
                    requestSpec
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

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // PUT API
    public String executePut(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        try {

            RestClient.RequestBodySpec requestSpec =
                    restClient
                            .put()
                            .uri(buildUrl(request));

            addHeaders(requestSpec, request);

            ResponseEntity<String> response =
                    requestSpec
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

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // DELETE API
    public String executeDelete(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        RestClient.RequestHeadersSpec<?> requestSpec =
                restClient
                        .delete()
                        .uri(buildUrl(request));

        addHeaders(requestSpec, request);

        ResponseEntity<String> response =
                requestSpec
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

        RestClient.RequestBodySpec requestSpec =
                restClient
                        .patch()
                        .uri(buildUrl(request));

        addHeaders(requestSpec, request);

        ResponseEntity<String> response =
                requestSpec
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

    // Add custom headers
    private void addHeaders(
            RestClient.RequestHeadersSpec<?> requestSpec,
            ApiRequest request) {

        if (request.getHeaders() != null) {

            request.getHeaders().forEach(
                    requestSpec::header
            );
        }
    }
    private String handleApiError(
            ApiRequest request,
            RestClientResponseException exception,
            long executionTime) {

        int statusCode = exception.getStatusCode().value();

        String responseBody = exception.getResponseBodyAsString();

        saveHistory(
                request,
                statusCode,
                responseBody,
                executionTime
        );

        return responseBody;
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