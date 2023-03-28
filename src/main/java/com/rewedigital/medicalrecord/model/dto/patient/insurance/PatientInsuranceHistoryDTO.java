package com.rewedigital.medicalrecord.model.dto.patient.insurance;

import com.rewedigital.medicalrecord.model.validation.StartDateBeforeEndDateValidation;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@StartDateBeforeEndDateValidation(first = "startDate", second = "endDate")
public class PatientInsuranceHistoryDTO {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}
