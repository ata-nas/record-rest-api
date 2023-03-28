package com.rewedigital.medicalrecord.model.dto.appointment;

import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingDiagnoseNameValidation;
import com.rewedigital.medicalrecord.model.validation.ExistingDoctorUicValidation;
import com.rewedigital.medicalrecord.model.validation.ExistingPatientUicValidation;

import jakarta.persistence.*;

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
public class AppointmentDTO {

    @NotBlank
    private String uic;

    @NotNull
    @PastOrPresent
    private LocalDate date;

    @NotBlank
    private String doctorUic;

    @NotBlank
    private String patientUic;

    @NotBlank
    private String description;

    @NotEmpty
    @ManyToMany
    @OrderBy
    private Set<@Valid DiagnoseDTO> diagnoses;

}
