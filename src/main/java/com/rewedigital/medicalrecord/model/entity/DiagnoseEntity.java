package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NaturalId;

@Getter
@Setter
@Entity
@Table(name = "diagnoses")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DiagnoseEntity extends BaseEntity {

    @NotBlank
    @NaturalId
    @Column(
            unique = true,
            nullable = false
    )
    @EqualsAndHashCode.Include
    private String name;

}
