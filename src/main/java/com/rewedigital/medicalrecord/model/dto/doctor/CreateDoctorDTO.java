package com.rewedigital.medicalrecord.model.dto.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rewedigital.medicalrecord.model.validation.ExistingSpecialtyNameValidation;

import com.rewedigital.medicalrecord.model.validation.NotExistingDoctorUicValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class CreateDoctorDTO {

    @NotBlank
    @NotExistingDoctorUicValidation
    private String uic;

    @NotBlank
    private String name;

    @NotNull
    @PastOrPresent
    private LocalDate birthDate;

    @NotNull
    @JsonProperty
    private boolean isGp;

    private Set<@ExistingSpecialtyNameValidation String> specialtiesNames;

}
