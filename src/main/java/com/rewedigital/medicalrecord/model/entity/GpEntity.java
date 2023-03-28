package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gps")
public class GpEntity extends DoctorEntity {

    @NotNull
    @MapsId
    private Long id;

}
