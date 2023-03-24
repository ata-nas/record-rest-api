package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "healthcare_agencies")
public class HealthcareAgencyEntity extends BaseEntity {

    @NotNull
    @PositiveOrZero
    @Column(name = "appointment_fee")
    private BigDecimal appointmentFee;

    @OneToMany(
            mappedBy = "healthcareAgency",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @OrderBy
    private Set<@Valid AppointmentEntity> appointmentsRecord;

}
