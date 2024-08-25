package com.sm1286.model;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tool")
@Table
public class Tool implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Tool code must not be null.")
    @Size(max = 15, message = "Tool code must be no more than {max} characters long.")
    @Column(length = 15, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type", referencedColumnName = "type_code", nullable = false)
    private ToolType type;

    @NotBlank(message = "Tool brand must not be null.")
    @Size(max = 55, message = "Tool brand must be no more than {max} characters long.")
    @Column(length = 55, nullable = false)
    private String brand;

    private Boolean active = true;

}

