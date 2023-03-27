package com.rewedigital.medicalrecord.model.dto.patient;

import com.rewedigital.medicalrecord.model.dto.patient.insurance.PatientInsuranceHistoryDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class PatientDTO {

    @NotBlank
    private String uic;

    @NotBlank
    private String name;

    private String gpUic;

    @NotNull
    private Set<@Valid PatientInsuranceHistoryDTO> insurances;

}
