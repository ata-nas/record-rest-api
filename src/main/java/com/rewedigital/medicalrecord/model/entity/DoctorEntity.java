package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NaturalId;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctors")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class DoctorEntity extends BaseEntity {

    @NotBlank
    @NaturalId
    @Column(
            unique = true,
            nullable = false
    )
    @EqualsAndHashCode.Include
    private String uic;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @PastOrPresent
    @Column(
            name = "birth_date",
            nullable = false
    )
    private LocalDate birthDate;

    // TODO Use MapperUtil to get valid specialties from db, otherwise will blow, cause im trying to create new ones!
    @ManyToMany
    @OrderBy
    @JoinTable(
            name = "doctors_specialties",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id")
    )
    private Set<@Valid SpecialtyEntity> specialties;

    @Column(nullable = false)
    private boolean deleted;

}
