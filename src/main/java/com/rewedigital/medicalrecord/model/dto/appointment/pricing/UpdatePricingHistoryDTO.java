package com.rewedigital.medicalrecord.model.dto.appointment.pricing;

import com.rewedigital.medicalrecord.model.validation.NotConflictingPricingHistoryDatesValidation;
import com.rewedigital.medicalrecord.model.validation.StartDateBeforeEndDateValidation;
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
public class UpdatePricingHistoryDTO {

    @NotNull
    @PositiveOrZero
    private BigDecimal appointmentFees;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

}
