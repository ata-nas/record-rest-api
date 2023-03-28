package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NaturalId;

import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "healthcare_agencies")
public class HealthcareAgencyEntity extends BaseEntity {

    @NotBlank
    @NaturalId
    @Column(
            unique = true,
            nullable = false
    )
    private String country;

    @NotNull
    @PositiveOrZero
    @Column(
            name = "appointment_fees",
            nullable = false
    )
    private BigDecimal appointmentFees;

// TODO Create history table with current fee for current appointment. naive approach!
    @OneToMany(
            mappedBy = "healthcareAgency",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    @OrderBy
    private Set<@Valid AppointmentEntity> appointmentsRecord; // TODO Remove ?

}
