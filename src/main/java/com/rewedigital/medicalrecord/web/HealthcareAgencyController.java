package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.healthcare.HealthcareAgencyDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingHealthcareAgencyCountryValidation;
import com.rewedigital.medicalrecord.service.HealthcareAgencyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/healthcare")
@RequiredArgsConstructor
@Validated
public class HealthcareAgencyController {

    private final HealthcareAgencyService healthcareAgencyService;

    @GetMapping("/{country}")
    public ResponseEntity<HealthcareAgencyDTO> healthcare(
            @PathVariable
            @NotBlank
            @ExistingHealthcareAgencyCountryValidation(message = "Invalid path! HealthcareAgency with given {country} does not exist!")
            String country) {
        return ResponseEntity.ok(healthcareAgencyService.getByCountryToDTO(country));
    }

    @PutMapping("/{country}")
    public ResponseEntity<HealthcareAgencyDTO> healthcareUpdateFees(
            @PathVariable
            @NotBlank
            @ExistingHealthcareAgencyCountryValidation(message = "Invalid path! HealthcareAgency with given {country} does not exist!")
            String country,
            @RequestBody @Valid HealthcareAgencyDTO healthcareAgencyDTO
    ) {
        return ResponseEntity.ok(healthcareAgencyService.update(country, healthcareAgencyDTO));
    }

}
