package com.rewedigital.medicalrecord.model.dto.patient;

import com.rewedigital.medicalrecord.model.dto.patient.insurance.PatientInsuranceHistoryDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingGpUicValidation;
import com.rewedigital.medicalrecord.model.validation.ExistingPatientUicValidation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UpdatePatientDTO {

    @NotBlank
    @ExistingPatientUicValidation
    private String uic;

    @NotBlank
    private String name;

    @ExistingGpUicValidation
    private String gpUic;

    @NotNull
    private Set<@Valid PatientInsuranceHistoryDTO> insurances;

}
