package com.sm1286.controller.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolRequest {
    private String code;
    private String type;
    private String brand;
    private Boolean active = true;
}
