package com.rewedigital.medicalrecord.model.dto.doctor;

import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;

import com.rewedigital.medicalrecord.model.validation.ExistingSpecialtyNameValidation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class UpdateDoctorDTO {

    @NotBlank
    private String name;

    @NotNull
    @PastOrPresent
    private LocalDate birthDate;

    private Set<@ExistingSpecialtyNameValidation String> specialtiesNames;

}
