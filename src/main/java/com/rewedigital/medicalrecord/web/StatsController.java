package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PercentageInsuredPatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.CountDoctorIncomeHigherThanDTO;
import com.rewedigital.medicalrecord.service.StatsService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare/bulgaria/stats")
@RequiredArgsConstructor
@Validated
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/patients/insured")
    public ResponseEntity<List<PatientDTO>> patientsCurrentlyInsured() {
        return ResponseEntity.ok(statsService.getAllPatientsCurrentlyInsured());
    }

    @GetMapping("/patients/not-insured")
    public ResponseEntity<List<PatientDTO>> patientsCurrentlyNotInsured() {
        return ResponseEntity.ok(statsService.getAllPatientsCurrentlyNotInsured());
    }

    @GetMapping("/patients/percent/insured")
    public ResponseEntity<PercentageInsuredPatientDTO> patientsCurrentlyInsuredPercent() {
        return ResponseEntity.ok(statsService.getPercentageCurrentlyInsured());
    }

    @GetMapping("/patients/percent/not-insured")
    public ResponseEntity<PercentageInsuredPatientDTO> patientsCurrentlyNotInsuredPercent() {
        return ResponseEntity.ok(statsService.getPercentageCurrentlyNotInsured());
    }

    @GetMapping("/doctors/income/{income}")
    public ResponseEntity<CountDoctorIncomeHigherThanDTO> doctorsCountDoctorsWithHigherIncomeThanGiven(
            @PathVariable Long income
    ) {
        return ResponseEntity.ok(statsService.countDoctorsWithHigherIncomeThanGiven(income));
    }

}
