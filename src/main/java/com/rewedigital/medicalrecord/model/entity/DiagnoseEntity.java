package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "diagnoses")
public class DiagnoseEntity extends BaseEntity {

    @Column
    private String name;

}
