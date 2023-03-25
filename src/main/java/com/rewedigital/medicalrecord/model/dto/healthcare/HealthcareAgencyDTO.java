package com.rewedigital.medicalrecord.model.dto.healthcare;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class HealthcareAgencyDTO {

    @NotNull
    private BigDecimal appointmentFees;

}
