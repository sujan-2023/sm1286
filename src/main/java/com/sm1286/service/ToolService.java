package com.sm1286.service;

import com.sm1286.controller.request.ToolRequest;
import com.sm1286.controller.response.ToolResponse;
import com.sm1286.exception.RequestValidationException;
import com.sm1286.mapper.ToolMapper;
import com.sm1286.model.Tool;
import com.sm1286.model.ToolType;
import com.sm1286.repository.ToolRepository;
import com.sm1286.repository.ToolTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;
    private final ToolTypeRepository toolTypeRepository;
    private final Validator validator;

    public Optional<Tool> createTool(ToolRequest toolRequest) throws ValidationException {
        final Tool tool = ToolMapper.toToolEntity(toolRequest);
        validateAndSetType(toolRequest, tool);
        validate(tool, true);
        return Optional.of(toolRepository.save(tool));
    }

    public Optional<Tool> updateTool(String toolCode, ToolRequest toolRequest) throws ValidationException {
        final Optional<Tool> existingToolOpt = getToolByCode(toolCode);
        if (existingToolOpt.isPresent()) {
            final Tool existingTool = existingToolOpt.get();
            // Update fields as necessary
            validateAndSetType(toolRequest, existingTool);
            existingTool.setBrand(toolRequest.getBrand());
            existingTool.setActive(toolRequest.getActive());
            // validate
            validate(existingTool, false);
            // Save the updated tool
            return Optional.of(toolRepository.save(existingTool));
        }
        return Optional.empty();
    }

    public Optional<Tool> getToolByCode(String code) {
        return toolRepository.findByCode(code);
    }

    public boolean existsByCode(String code) {
        return toolRepository.existsByCode(code);
    }

    public Page<ToolResponse> getAllTools(Boolean active, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size);

        final Page<Tool> toolPage = toolRepository.findByActive(active, pageable);
        final List<ToolResponse> toolResponses = toolPage.getContent().stream()
                .map(ToolMapper::toToolResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(toolResponses, pageable, toolPage.getTotalElements());
    }

    public void validateAndSetType(ToolRequest toolRequest, Tool tool) throws ValidationException {
        final Optional<ToolType> toolType = toolTypeRepository.findByTypeCode(toolRequest.getType());
        if (!toolType.isPresent()) {
            throw new ValidationException("Tool type " + toolRequest.getType() + " doesn't exist.");
        }
        tool.setType(toolType.get());
    }

    public void validate(Tool tool, boolean isAdd) throws RequestValidationException {
        final Set<ConstraintViolation<Tool>> violations = validator.validate(tool);
        final List<String> validationErrors = new ArrayList<>();
        if (!violations.isEmpty()) {
            for (final ConstraintViolation<Tool> violation : violations) {
                validationErrors.add(violation.getMessage());
            }
        }
        if (isAdd && existsByCode(tool.getCode())) {
            validationErrors.add("Tool already exist with code: " + tool.getCode());
        }
        if (!validationErrors.isEmpty()) {
            throw new RequestValidationException(validationErrors.stream().collect(Collectors.joining("|")));
        }
    }
}
