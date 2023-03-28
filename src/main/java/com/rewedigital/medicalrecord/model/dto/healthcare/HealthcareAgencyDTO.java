package com.rewedigital.medicalrecord.model.dto.healthcare;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HealthcareAgencyDTO {

    @NotNull
    @PositiveOrZero
    private BigDecimal appointmentFees;

}
