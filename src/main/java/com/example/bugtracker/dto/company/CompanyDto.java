package com.example.bugtracker.dto.company;

import com.example.bugtracker.dto.IdNameDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Company DTO object")
public class CompanyDto extends IdNameDTO {
    @Schema(description = "Address where company is located")
    private String address;
}
