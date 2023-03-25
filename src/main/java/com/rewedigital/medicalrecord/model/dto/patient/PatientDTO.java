package com.rewedigital.medicalrecord.model.dto.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {

    @NotBlank
    private String uic;

    @NotBlank
    private String name;

    @NotEmpty
    private Boolean insured;

    private String gpUic;

}
