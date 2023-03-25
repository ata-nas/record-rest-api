package com.rewedigital.medicalrecord.model.dto.diagnose;

import com.rewedigital.medicalrecord.model.validation.NotExistingDiagnoseValidation;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDiagnoseDTO {

    @NotBlank
    @NotExistingDiagnoseValidation
    private String name;

}
