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

        String response =
                apiExecutionService.executeGet(request);

        return ResponseEntity.ok(response);
    }
}