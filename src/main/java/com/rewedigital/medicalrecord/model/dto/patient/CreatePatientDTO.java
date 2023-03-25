package com.rewedigital.medicalrecord.model.dto.patient;

import com.rewedigital.medicalrecord.model.validation.ExistingGpUicValidation;
import com.rewedigital.medicalrecord.model.validation.NotExistingPatientValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePatientDTO {

    @NotBlank
    @NotExistingPatientValidation
    private String uic;

    @NotBlank
    private String name;

    @NotNull
    private Boolean insured;

    @ExistingGpUicValidation
    private String gpUic;

}
