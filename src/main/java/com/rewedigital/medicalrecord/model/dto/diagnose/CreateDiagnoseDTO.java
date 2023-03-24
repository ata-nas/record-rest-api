package com.rewedigital.medicalrecord.model.dto.diagnose;

import com.rewedigital.medicalrecord.model.validation.diagnose.UniqueDiagnoseValidation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDiagnoseDTO {

    @NotBlank
    @Size(min = 5)
    @UniqueDiagnoseValidation
    private String name;

}
