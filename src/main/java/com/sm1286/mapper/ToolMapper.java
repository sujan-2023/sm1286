package com.sm1286.mapper;

import com.sm1286.controller.request.ToolRequest;
import com.sm1286.controller.response.ToolResponse;
import com.sm1286.controller.response.ToolTypeResponse;
import com.sm1286.model.Tool;

public class ToolMapper {
    public static Tool toToolEntity(ToolRequest toolRequest) {
        return Tool.builder()
                .code(toolRequest.getCode())
                .brand(toolRequest.getBrand())
                .active(toolRequest.getActive())
                .build();
    }

    public static ToolResponse toToolResponse(Tool tool) {
        return ToolResponse.builder()
                .id(tool.getId())
                .code(tool.getCode())
                .type(ToolTypeResponse.builder()
                        .id(tool.getType().getId())
                        .type(tool.getType().getTypeCode())
                        .dailyCharge(tool.getType().getDailyCharge())
                        .weekdayCharge(tool.getType().getWeekdayCharge())
                        .weekendCharge(tool.getType().getWeekendCharge())
                        .holidayCharge(tool.getType().getHolidayCharge())
                        .build())
                .brand(tool.getBrand())
                .active(tool.getActive())
                .build();
    }

}
