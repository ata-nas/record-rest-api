package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "gps")
public class GpEntity extends DoctorEntity {

    @MapsId
    private Long id;

}
