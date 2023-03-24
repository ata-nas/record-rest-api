package com.rewedigital.medicalrecord.model.dto.diagnose;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiagnoseDTO {

    @NotBlank
    private String name;

}
