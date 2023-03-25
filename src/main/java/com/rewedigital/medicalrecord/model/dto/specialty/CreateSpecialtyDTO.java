package com.rewedigital.medicalrecord.model.dto.specialty;

import com.rewedigital.medicalrecord.model.validation.NotExistingSpecialtyValidation;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSpecialtyDTO {

    @NotBlank
    @NotExistingSpecialtyValidation
    private String name;

}
