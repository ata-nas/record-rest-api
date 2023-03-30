package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.NaturalId;

import java.util.LinkedHashSet;
import java.util.Set;

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

    @Valid
    @ManyToOne //(fetch = FetchType.LAZY) //TODO make @Transactional service and optimise all queries
    private GpEntity gp;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy
    @JoinTable(
            name = "patients_insurances",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id")
    )
    private Set<@Valid PatientInsuranceHistoryEntity> insurances;

    @Column(nullable = false)
    private boolean deleted;

    /**
     *  Adder method for Mapstruct Strategy, otherwise it rewrites the whole collection and the behavior is not desired.
     * @param toAdd - the PatientInsuranceHistoryEntity to be added
     * @return this - PatientEntity
     */
    public PatientEntity addInsurance(PatientInsuranceHistoryEntity toAdd) {
        if (insurances == null) {
            insurances = new LinkedHashSet<>();
        }
        insurances.add(toAdd);
        return this;
    }

}
