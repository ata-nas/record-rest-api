package com.rewedigital.medicalrecord.model.dto.appointment;

import com.rewedigital.medicalrecord.model.validation.ExistingDiagnoseNameValidation;
import com.rewedigital.medicalrecord.model.validation.ExistingDoctorUicValidation;
import com.rewedigital.medicalrecord.model.validation.ExistingPatientUicValidation;
import com.rewedigital.medicalrecord.model.validation.NotExistingAppointmentUicValidation;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.OrderBy;

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
public class CreateAppointmentDTO {

    @NotBlank
    @NotExistingAppointmentUicValidation
    private String uic;

    @NotNull
    @PastOrPresent
    private LocalDate date;

    @NotBlank
    @ExistingDoctorUicValidation
    private String doctorUic;

    @NotBlank
    @ExistingPatientUicValidation
    private String patientUic;

    @NotBlank
    private String description;

    @NotEmpty
    @ManyToMany
    @OrderBy
    private Set<@ExistingDiagnoseNameValidation String> diagnoses;

}
