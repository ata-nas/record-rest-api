package com.rewedigital.medicalrecord.model.dto.patient;

import com.rewedigital.medicalrecord.model.dto.patient.insurance.CreatePatientInsuranceHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.patient.insurance.PatientInsuranceHistoryDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingGpUicValidation;
import com.rewedigital.medicalrecord.model.validation.NotExistingPatientUicValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreatePatientDTO {

    @NotBlank
    @NotExistingPatientUicValidation
    private String uic;

    @NotBlank
    private String name;

    @ExistingGpUicValidation
    private String gpUic;

    @NotNull
    private Set<@Valid CreatePatientInsuranceHistoryDTO> insurances;

}
