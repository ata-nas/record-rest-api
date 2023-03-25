package com.rewedigital.medicalrecord.model.dto.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePatientDTO {

    @NotBlank
    private String name;

    @NotNull
    private Boolean insured;

    private String gpUic;

}
