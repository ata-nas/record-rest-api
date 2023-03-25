package com.rewedigital.medicalrecord.model.dto.patient;

import com.rewedigital.medicalrecord.model.validation.patient.UniquePatientValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePatientDTO {

    @NotBlank
    @UniquePatientValidation
    private String uic;

    @NotBlank
    private String name;

    @NotNull
    private Boolean insured;

    private String gpUic;
    // TODO make custom validation that checks: if null then return null, if not null,
    // check in gp repo if exist, if not throw exception, instead of doing it in PatientMapper

}
