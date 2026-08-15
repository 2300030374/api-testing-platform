package com.sasi.api_testing_platform.service;

import com.sasi.api_testing_platform.dto.ApiExecutionResponse;
import com.sasi.api_testing_platform.dto.ApiRequest;
import com.sasi.api_testing_platform.entity.ApiTestHistory;
import com.sasi.api_testing_platform.repository.ApiTestHistoryRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.util.UriComponentsBuilder;

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

    // =========================================================
    // BUILD URL WITH QUERY PARAMETERS
    // =========================================================

    private String buildUrl(ApiRequest request) {

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromUriString(request.getUrl());

        Map<String, String> queryParams =
                request.getQueryParams();

        if (queryParams != null) {
            queryParams.forEach(builder::queryParam);
        }

        return builder.toUriString();
    }

    // =========================================================
    // GET API
    // =========================================================

    public ApiExecutionResponse executeGet(ApiRequest request) {

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

            return new ApiExecutionResponse(
                    response.getStatusCode().value(),
                    response.getBody(),
                    executionTime
            );

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );

        } catch (ResourceAccessException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleConnectionError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // =========================================================
    // POST API
    // =========================================================

    public ApiExecutionResponse executePost(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        try {

            RestClient.RequestBodySpec requestSpec =
                    restClient
                            .post()
                            .uri(buildUrl(request));

            addHeaders(requestSpec, request);

            ResponseEntity<String> response =
                    requestSpec
                            .body(
                                    request.getBody() == null
                                            ? ""
                                            : request.getBody()
                            )
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

            return new ApiExecutionResponse(
                    response.getStatusCode().value(),
                    response.getBody(),
                    executionTime
            );

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );

        } catch (ResourceAccessException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleConnectionError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // =========================================================
    // PUT API
    // =========================================================

    public ApiExecutionResponse executePut(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        try {

            RestClient.RequestBodySpec requestSpec =
                    restClient
                            .put()
                            .uri(buildUrl(request));

            addHeaders(requestSpec, request);

            ResponseEntity<String> response =
                    requestSpec
                            .body(
                                    request.getBody() == null
                                            ? ""
                                            : request.getBody()
                            )
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

            return new ApiExecutionResponse(
                    response.getStatusCode().value(),
                    response.getBody(),
                    executionTime
            );

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );

        } catch (ResourceAccessException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleConnectionError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // =========================================================
    // DELETE API
    // =========================================================

    public ApiExecutionResponse executeDelete(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        try {

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

            return new ApiExecutionResponse(
                    response.getStatusCode().value(),
                    response.getBody(),
                    executionTime
            );

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );

        } catch (ResourceAccessException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleConnectionError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // =========================================================
    // PATCH API
    // =========================================================

    public ApiExecutionResponse executePatch(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        try {

            RestClient.RequestBodySpec requestSpec =
                    restClient
                            .patch()
                            .uri(buildUrl(request));

            addHeaders(requestSpec, request);

            ResponseEntity<String> response =
                    requestSpec
                            .body(
                                    request.getBody() == null
                                            ? ""
                                            : request.getBody()
                            )
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

            return new ApiExecutionResponse(
                    response.getStatusCode().value(),
                    response.getBody(),
                    executionTime
            );

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );

        } catch (ResourceAccessException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleConnectionError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // =========================================================
    // HEAD API
    // =========================================================

    public ApiExecutionResponse executeHead(ApiRequest request) {

        long startTime = System.currentTimeMillis();

        try {

            RestClient.RequestHeadersSpec<?> requestSpec =
                    restClient
                            .head()
                            .uri(buildUrl(request));

            addHeaders(requestSpec, request);

            ResponseEntity<Void> response =
                    requestSpec
                            .retrieve()
                            .toBodilessEntity();

            long executionTime =
                    System.currentTimeMillis() - startTime;

            saveHistory(
                    request,
                    response.getStatusCode().value(),
                    "",
                    executionTime
            );

            return new ApiExecutionResponse(
                    response.getStatusCode().value(),
                    "",
                    executionTime
            );

        } catch (RestClientResponseException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleApiError(
                    request,
                    exception,
                    executionTime
            );

        } catch (ResourceAccessException exception) {

            long executionTime =
                    System.currentTimeMillis() - startTime;

            return handleConnectionError(
                    request,
                    exception,
                    executionTime
            );
        }
    }

    // =========================================================
    // ADD CUSTOM HEADERS
    // =========================================================

    private void addHeaders(
            RestClient.RequestHeadersSpec<?> requestSpec,
            ApiRequest request) {

        if (request.getHeaders() != null) {

            request.getHeaders().forEach(
                    requestSpec::header
            );
        }
    }

    // =========================================================
    // HANDLE HTTP API ERROR
    // =========================================================

    private ApiExecutionResponse handleApiError(
            ApiRequest request,
            RestClientResponseException exception,
            long executionTime) {

        int statusCode =
                exception.getStatusCode().value();

        String responseBody =
                exception.getResponseBodyAsString();

        saveHistory(
                request,
                statusCode,
                responseBody,
                executionTime
        );

        return new ApiExecutionResponse(
                statusCode,
                responseBody,
                executionTime
        );
    }

    // =========================================================
    // HANDLE CONNECTION ERROR
    // =========================================================

    private ApiExecutionResponse handleConnectionError(
            ApiRequest request,
            ResourceAccessException exception,
            long executionTime) {

        String errorMessage =
                exception.getMessage();

        saveHistory(
                request,
                0,
                errorMessage,
                executionTime
        );

        return new ApiExecutionResponse(
                0,
                errorMessage,
                executionTime
        );
    }

    // =========================================================
    // SAVE API EXECUTION HISTORY
    // =========================================================

    private void saveHistory(
            ApiRequest request,
            int statusCode,
            String responseBody,
            long executionTime) {

        ApiTestHistory history =
                new ApiTestHistory();

        history.setUrl(request.getUrl());
        history.setMethod(request.getMethod());
        history.setStatusCode(statusCode);
        history.setResponse(responseBody);
        history.setExecutionTime(executionTime);
        history.setExecutedAt(LocalDateTime.now());

        historyRepository.save(history);
    }
}