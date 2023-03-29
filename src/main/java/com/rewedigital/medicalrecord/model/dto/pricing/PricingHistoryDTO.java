package com.rewedigital.medicalrecord.model.dto.pricing;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class PricingHistoryDTO {

    @NotBlank
    private String issueNo;

    @NotNull
    @PositiveOrZero
    private BigDecimal appointmentFees;

    @NotNull
    private LocalDate fromDate;

}
