package com.sm1286.controller.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ToolTypeResponse {
    private Integer id;
    private String type;
    private Double dailyCharge;
    private Boolean weekdayCharge;
    private Boolean weekendCharge;
    private Boolean holidayCharge;
}
