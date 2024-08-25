package com.sm1286.model;

import com.sm1286.validation.PositiveDouble;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tool_type")
@Table
public class ToolType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Tool code must not be null.")
    @Size(max = 15, message = "Tool code must be no more than {max} characters long.")
    @Column(name="type_code", length = 15, nullable = false)
    private String typeCode;

    @NotNull(message = "Daily charge cannot be null.")
    @Column(name="daily_charge", nullable = false)
    @PositiveDouble(message = "Daily charge must be a positive number.")
    private Double dailyCharge;

    @NotNull(message = "Weekday charge cannot be null.")
    @Column(name="weekday_charge", nullable = false)
    private Boolean weekdayCharge;

    @NotNull(message = "Weekend charge cannot be null.")
    @Column(name="weekend_charge", nullable = false)
    private Boolean weekendCharge;

    @NotNull(message = "Holiday charge cannot be null.")
    @Column(name="holiday_charge", nullable = false)
    private Boolean holidayCharge;
}

