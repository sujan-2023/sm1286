package com.sm1286.repository;

import com.sm1286.model.Tool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolRepository extends JpaRepository<Tool, Integer> {
    Optional<Tool> findByCode(String code);
    boolean existsByCode(String code);
    Page<Tool> findByActive(Boolean active, Pageable pageable);
}
