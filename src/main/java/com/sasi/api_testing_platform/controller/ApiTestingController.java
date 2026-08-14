package com.sasi.api_testing_platform.controller;

import com.sasi.api_testing_platform.dto.ApiRequest;
import com.sasi.api_testing_platform.service.ApiExecutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/testing")
public class ApiTestingController {

    private final ApiExecutionService apiExecutionService;

    public ApiTestingController(
            ApiExecutionService apiExecutionService) {
        this.apiExecutionService = apiExecutionService;
    }

    @PostMapping("/execute")
    public ResponseEntity<String> executeApi(
            @RequestBody ApiRequest request) {

        if ("GET".equalsIgnoreCase(request.getMethod())) {

            String response =
                    apiExecutionService.executeGet(request);

            return ResponseEntity.ok(response);
        }

        if ("POST".equalsIgnoreCase(request.getMethod())) {

            String response =
                    apiExecutionService.executePost(request);

            return ResponseEntity.ok(response);
        }
        if ("PUT".equalsIgnoreCase(request.getMethod())) {

            String response =
                    apiExecutionService.executePut(request);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .badRequest()
                .body("Unsupported HTTP method: " + request.getMethod());
    }
}