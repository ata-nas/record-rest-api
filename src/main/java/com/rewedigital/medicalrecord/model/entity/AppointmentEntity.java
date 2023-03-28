package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NaturalId;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "appointments")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class AppointmentEntity extends BaseEntity {

    @NotBlank
    @NaturalId
    @Column(
            unique = true,
            nullable = false
    )
    @EqualsAndHashCode.Include
    private String uic;

    @NotNull
    @PastOrPresent
    @Column(
            name = "date_time",
            nullable = false
    )
    private LocalDateTime dateTime;

    @Valid
    @NotNull
    @ManyToOne
    private DoctorEntity doctor;

    @Valid
    @NotNull
    @ManyToOne
    private PatientEntity patient;

    @NotEmpty
    @ManyToMany
    @OrderBy
    @JoinTable(
            name = "appointments_diagnoses",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id")
    )
    private Set<@Valid DiagnoseEntity> diagnoses;

    @NotBlank
    @Column(
            nullable = false
    )
    private String description;

    @Valid
    @NotNull
    @ManyToOne
    @JoinColumn(name = "price_history_id")
    private PricingHistoryEntity price;

}
