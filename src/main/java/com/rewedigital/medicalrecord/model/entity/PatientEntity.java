package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@Entity
@Table(name = "patients")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class PatientEntity extends BaseEntity {

    @NotBlank
    @Column(
            unique = true,
            nullable = false
    )
    @NaturalId
    @EqualsAndHashCode.Include
    private String uic;

    @NotBlank
    @Column(
            nullable = false
    )
    private String name;

    @NotBlank
    @Column(
            nullable = false
    )
    private Boolean insured;

    @Valid // TODO Check if is allowed null value here if used @Valid, I need to have this nullable if no GP!
    @ManyToOne
    private GpEntity gp;

}
