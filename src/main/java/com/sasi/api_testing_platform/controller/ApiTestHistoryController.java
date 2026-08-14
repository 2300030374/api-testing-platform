package com.sasi.api_testing_platform.controller;

import com.sasi.api_testing_platform.entity.ApiTestHistory;
import com.sasi.api_testing_platform.repository.ApiTestHistoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class ApiTestHistoryController {

    private final ApiTestHistoryRepository historyRepository;

    public ApiTestHistoryController(
            ApiTestHistoryRepository historyRepository) {

        this.historyRepository = historyRepository;
    }

    @GetMapping
    public ResponseEntity<List<ApiTestHistory>> getAllHistory() {

        return ResponseEntity.ok(
                historyRepository.findAll()
        );
    }
}