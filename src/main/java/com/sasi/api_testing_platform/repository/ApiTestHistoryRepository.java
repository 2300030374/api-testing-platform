package com.sasi.api_testing_platform.repository;

import com.sasi.api_testing_platform.entity.ApiTestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiTestHistoryRepository
        extends JpaRepository<ApiTestHistory, Long> {
}