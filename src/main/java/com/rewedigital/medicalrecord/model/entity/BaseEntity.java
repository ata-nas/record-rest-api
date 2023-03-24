package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter(AccessLevel.NONE)
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
