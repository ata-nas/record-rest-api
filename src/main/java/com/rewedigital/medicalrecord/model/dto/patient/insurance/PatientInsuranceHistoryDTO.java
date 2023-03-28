package com.rewedigital.medicalrecord.model.dto.patient.insurance;

import com.rewedigital.medicalrecord.model.validation.StartDateBeforeEndDateValidation;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@StartDateBeforeEndDateValidation(first = "startDateTime", second = "endDateTime")
public class PatientInsuranceHistoryDTO {

    @NotNull
    private LocalDateTime startDateTime;

    @NotNull
    private LocalDateTime endDateTime;

}
