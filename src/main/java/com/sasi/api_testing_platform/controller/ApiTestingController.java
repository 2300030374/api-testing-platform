package com.sasi.api_testing_platform.controller;

import com.sasi.api_testing_platform.dto.ApiRequest;
import com.sasi.api_testing_platform.service.ApiExecutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/testing")
public class ApiTestingController {

    private final ApiExecutionService apiExecutionService;

    public ApiTestingController(ApiExecutionService apiExecutionService) {
        this.apiExecutionService = apiExecutionService;
    }

    @PostMapping("/execute")
    public ResponseEntity<?> executeApi(
            @RequestBody ApiRequest request) {

        // Step 150: Validate URL
        if (request.getUrl() == null ||
                request.getUrl().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body("URL is required");
        }

        // Step 151: Validate HTTP method
        if (request.getMethod() == null ||
                request.getMethod().isBlank()) {

            return ResponseEntity
                    .badRequest()
                    .body("HTTP method is required");
        }

        // Step 152: Normalize HTTP method
        String method =
                request.getMethod()
                        .trim()
                        .toUpperCase();

        // Step 153: Validate URL format
        try {
            URI.create(request.getUrl());
        } catch (IllegalArgumentException exception) {

            return ResponseEntity
                    .badRequest()
                    .body("Invalid URL");
        }

        switch (method) {

            case "GET":
                return ResponseEntity.ok(
                        apiExecutionService.executeGet(request)
                );

            case "POST":
                return ResponseEntity.ok(
                        apiExecutionService.executePost(request)
                );

            case "PUT":
                return ResponseEntity.ok(
                        apiExecutionService.executePut(request)
                );

            case "DELETE":
                return ResponseEntity.ok(
                        apiExecutionService.executeDelete(request)
                );

            case "PATCH":
                return ResponseEntity.ok(
                        apiExecutionService.executePatch(request)
                );

            default:
                return ResponseEntity
                        .badRequest()
                        .body("Unsupported HTTP method: " + method);
        }
    }
}