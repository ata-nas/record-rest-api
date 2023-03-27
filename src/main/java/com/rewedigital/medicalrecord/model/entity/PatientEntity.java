package com.rewedigital.medicalrecord.model.entity;

import jakarta.persistence.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.experimental.SuperBuilder;
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

    @Valid // TODO Check if is allowed null value here if used @Valid, I need to have this nullable if no GP!
    @ManyToOne //(fetch = FetchType.LAZY) //TODO make @Transactional service and optimise all queries
    private GpEntity gp;

    // TODO Create history table for if insured on current date!

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy
    @JoinTable(
            name = "patients_insurances",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id")
    )
    private Set<@Valid PatientInsuranceHistoryEntity> insurances;

    public PatientEntity addInsurance(PatientInsuranceHistoryEntity toAdd) {
        // adder method for Mapstruct Strategy, otherwise it rewrites the whole collection and the behavior is not desired
        if (insurances == null) {
            insurances = new LinkedHashSet<>();
        }
        insurances.add(toAdd);
        return this;
    }

}
