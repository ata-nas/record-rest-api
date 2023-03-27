package com.rewedigital.medicalrecord.model.dto.doctor;

import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
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
public class DoctorDTO {

    @NotBlank
    private String uic;

    @NotBlank
    private String name;

    @NotNull
    @PastOrPresent
    private LocalDate birthDate;

    @NotEmpty
    private Set<SpecialtyDTO> specialties;

}
