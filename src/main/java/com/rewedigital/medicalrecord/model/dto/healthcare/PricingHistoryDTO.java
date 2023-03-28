package com.rewedigital.medicalrecord.model.dto.healthcare;

import com.rewedigital.medicalrecord.model.validation.StartDateBeforeEndDateValidation;
import com.rewedigital.medicalrecord.model.validation.tmp.NotConflictingPricingHistoryDatesValidation;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@StartDateBeforeEndDateValidation(first = "startDate", second = "endDate")
@NotConflictingPricingHistoryDatesValidation(first = "startDate", second = "endDate")
public class PricingHistoryDTO {

    @NotNull
    @PositiveOrZero
    private BigDecimal appointmentFees;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}
