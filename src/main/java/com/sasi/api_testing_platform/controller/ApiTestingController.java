package com.sasi.api_testing_platform.controller;

import com.sasi.api_testing_platform.dto.ApiExecutionResponse;
import com.sasi.api_testing_platform.dto.ApiRequest;
import com.sasi.api_testing_platform.service.ApiExecutionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        String method = request.getMethod().toUpperCase();

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