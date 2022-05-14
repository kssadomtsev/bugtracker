package com.example.bugtracker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Base DTO object")
public class IdNameDTO {
    @Schema(description = "Unique id")
    private Long id;
    @Schema(description = "Name")
    private String name;
}
