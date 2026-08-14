package com.sasi.api_testing_platform.service;

import com.sasi.api_testing_platform.dto.ApiRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class ApiExecutionService {

    private final RestClient restClient;

    public ApiExecutionService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    public String executeGet(ApiRequest request) {

        ResponseEntity<String> response = restClient
                .get()
                .uri(request.getUrl())
                .retrieve()
                .toEntity(String.class);

        return response.getBody();
    }
    public String executePost(ApiRequest request) {

        ResponseEntity<String> response = restClient
                .post()
                .uri(request.getUrl())
                .header("Content-Type", "application/json")
                .body(request.getBody())
                .retrieve()
                .toEntity(String.class);

        return response.getBody();
    }
    public String executePut(ApiRequest request) {

        ResponseEntity<String> response = restClient
                .put()
                .uri(request.getUrl())
                .header("Content-Type", "application/json")
                .body(request.getBody())
                .retrieve()
                .toEntity(String.class);

        return response.getBody();
    }
}