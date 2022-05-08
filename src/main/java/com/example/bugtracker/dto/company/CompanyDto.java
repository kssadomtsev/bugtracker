package com.example.bugtracker.dto.company;

import com.example.bugtracker.dto.IdNameDTO;
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
public class CompanyDto extends IdNameDTO {
    private String address;
}
