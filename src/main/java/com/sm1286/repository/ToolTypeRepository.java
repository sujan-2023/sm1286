package com.sm1286.repository;

import com.sm1286.model.ToolType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ToolTypeRepository extends JpaRepository<ToolType, Integer> {
    Optional<ToolType> findByTypeCode(String typeCode);

}
