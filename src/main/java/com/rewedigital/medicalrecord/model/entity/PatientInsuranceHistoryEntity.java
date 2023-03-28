package com.rewedigital.medicalrecord.model.entity;

import com.rewedigital.medicalrecord.model.validation.StartDateBeforeEndDateValidation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "insurances")
@EqualsAndHashCode(callSuper = false)
@StartDateBeforeEndDateValidation(first = "startDateTime", second = "endDateTime")
public class PatientInsuranceHistoryEntity extends BaseEntity {

    @NotNull
    @Column(name = "start_date_time", nullable = false)
    private LocalDateTime startDateTime;

    @NotNull
    @Column(name = "end_date_time", nullable = false)
    private LocalDateTime endDateTime;

}
