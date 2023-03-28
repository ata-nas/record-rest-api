package com.rewedigital.medicalrecord.model.entity;

import com.rewedigital.medicalrecord.model.validation.StartDateBeforeEndDateValidation;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pricing_hisotry")
@StartDateBeforeEndDateValidation(first = "startDate", second = "endDate")
public class PricingHistoryEntity extends BaseEntity {

    @NotNull
    @PositiveOrZero
    @Column(
            name = "appointment_fees",
            nullable = false
    )
    private BigDecimal appointmentFees;

    @NotNull
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

}
