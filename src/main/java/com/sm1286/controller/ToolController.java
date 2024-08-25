package com.sm1286.controller;

import static com.sm1286.controller.ToolController.BASE_URL;

import com.sm1286.controller.request.ToolRequest;
import com.sm1286.controller.response.ToolResponse;
import com.sm1286.mapper.ToolMapper;
import com.sm1286.model.Tool;
import com.sm1286.service.ToolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.bind.ValidationException;

@RestController
@RequestMapping(BASE_URL)
@RequiredArgsConstructor
@Log4j2
public class ToolController {

    public static final String BASE_URL = "/api/tools";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_PAGE_NUMBER = "0";
    private final ToolService toolService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<ToolResponse> getTools(
            @RequestParam Boolean active,
            @RequestParam(defaultValue = DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int size) {
        return toolService.getAllTools(active, page, size);
    }

    @GetMapping(value = "{toolCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToolResponse> getTool(@PathVariable String toolCode) {
        return toolService.getToolByCode(toolCode)
                .map(tool -> ResponseEntity.ok(ToolMapper.toToolResponse(tool)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToolResponse> createTool(@Valid @RequestBody ToolRequest toolRequest) throws ValidationException {
        final Tool savedTool = toolService.createTool(toolRequest)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tool creation failed"));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ToolMapper.toToolResponse(savedTool));
    }

    @PatchMapping(value = "{toolCode}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToolResponse> updateTool(@PathVariable String toolCode,
                                                   @Valid @RequestBody ToolRequest updatedToolRequest) throws ValidationException {
        return toolService.updateTool(toolCode, updatedToolRequest)
                .map(tool -> ResponseEntity.ok(ToolMapper.toToolResponse(tool)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

}
