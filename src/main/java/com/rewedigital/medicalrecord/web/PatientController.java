package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PercentNotInsuredPatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.service.PatientManagerService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare/bulgaria/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientManagerService patientManagerService;

    @GetMapping("/{uic}")
    public ResponseEntity<PatientDTO> patient(@PathVariable String uic) {
        return ResponseEntity.ok(patientManagerService.findByUicToDTO(uic));
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> patients() {
        return ResponseEntity.ok(patientManagerService.getAllPatientsToDTO());
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(
            @RequestBody @Valid CreatePatientDTO createPatientDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/api")
                                .path("/healthcare")
                                .path("/bulgaria")
                                .path("/patients")
                                .path("/" + createPatientDTO.getUic().toLowerCase())
                                .build().toUri()
                )
                .body(patientManagerService.createPatient(createPatientDTO));
    }

    @PutMapping("/{uic}")
    public ResponseEntity<PatientDTO> updatePatient(
            @PathVariable String uic,
            @RequestBody @Valid UpdatePatientDTO updatePatientDTO
    ) {
        return ResponseEntity.ok(patientManagerService.updatePatient(uic, updatePatientDTO));
    }

    @DeleteMapping("/{uic}")
    public ResponseEntity<PatientDTO> deletePatient(
            @PathVariable String uic
    ) {
        patientManagerService.deletePatientByUic(uic);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/not-insured")
    public ResponseEntity<List<PatientDTO>> patientsNotInsured() {
        return ResponseEntity.ok(patientManagerService.getAllPatientsInsuredFalseToDTO());

    }

    @GetMapping("/insured")
    public ResponseEntity<List<PatientDTO>> patientsInsured() {
        return ResponseEntity.ok(patientManagerService.getAllPatientsInsuredTrueToDTO());

    }

    @GetMapping("/percent/not-insured")
    public ResponseEntity<PercentNotInsuredPatientDTO> percentPatientsNotInsured() {
        return ResponseEntity.ok(patientManagerService.totalPercentNotInsuredPatients());
    }

    @GetMapping("/percent/insured")
    public ResponseEntity<PercentNotInsuredPatientDTO> percentPatientsInsured() {
        return ResponseEntity.ok(patientManagerService.totalPercentInsuredPatients());
    }

}
