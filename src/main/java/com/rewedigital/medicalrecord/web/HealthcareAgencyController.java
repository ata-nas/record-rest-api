package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.healthcare.HealthcareAgencyDTO;
import com.rewedigital.medicalrecord.service.HealthcareAgencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/healthcare")
@RequiredArgsConstructor
public class HealthcareAgencyController {

    private final HealthcareAgencyService healthcareAgencyService;

    @GetMapping("/{country}")
    public ResponseEntity<HealthcareAgencyDTO> healthcare(@PathVariable String country) {
        return ResponseEntity.ok(healthcareAgencyService.getFees(country));
    }

    @PutMapping("/{country}")
    public ResponseEntity<HealthcareAgencyDTO> healthcareUpdateFees(
            @PathVariable String country,
            @RequestBody HealthcareAgencyDTO healthcareAgencyDTO,
            BindingResult bindingResult
    ) {
        return ResponseEntity.ok(healthcareAgencyService.updateFees(country, healthcareAgencyDTO));
    }

}
