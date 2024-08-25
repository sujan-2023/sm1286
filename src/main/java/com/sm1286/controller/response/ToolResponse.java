package com.sm1286.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolResponse {
    private Integer id;
    private String code;
    private ToolTypeResponse type;
    private String brand;
    private Boolean active;
}
