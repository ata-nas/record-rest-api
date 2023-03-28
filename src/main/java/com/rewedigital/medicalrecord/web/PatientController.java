package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;

import com.rewedigital.medicalrecord.model.validation.ExistingPatientUicValidation;
import com.rewedigital.medicalrecord.service.PatientService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare/bulgaria/patients")
@RequiredArgsConstructor
@Validated
public class PatientController {

    private final PatientService patientService;

    @GetMapping("/{uic}")
    public ResponseEntity<PatientDTO> patient(
            @PathVariable
            @NotBlank
            @ExistingPatientUicValidation(message = "Illegal path! Patient with given {uic} does not exist!")
            String uic
    ) {
        return ResponseEntity.ok(patientService.getPatientByUicToDTO(uic));
    }

    @GetMapping
    public ResponseEntity<List<PatientDTO>> patients() {
        return ResponseEntity.ok(patientService.getAllPatientsToDTO());
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
                .body(patientService.createPatient(createPatientDTO));
    }

    @PutMapping("/{uic}")
    public ResponseEntity<PatientDTO> updatePatient(
            @PathVariable
            @NotBlank
            @ExistingPatientUicValidation(message = "Illegal path! Patient with given {uic} does not exist!")
            String uic,
            @RequestBody @Valid UpdatePatientDTO updatePatientDTO
    ) {
        return ResponseEntity.ok(patientService.updatePatient(uic, updatePatientDTO));
    }

    @DeleteMapping("/{uic}")
    public ResponseEntity<PatientDTO> deletePatient(
            @PathVariable
            @NotBlank
            @ExistingPatientUicValidation(message = "Illegal path! Patient with given {uic} does not exist!")
            String uic
    ) {
        patientService.deletePatientByUic(uic);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/not-insured")
//    public ResponseEntity<List<PatientDTO>> patientsNotInsured() {
//        return ResponseEntity.ok(patientService.getAllPatientsInsuredFalseToDTO());
//
//    }
//
//    @GetMapping("/insured")
//    public ResponseEntity<List<PatientDTO>> patientsInsured() {
//        return ResponseEntity.ok(patientService.getAllPatientsInsuredTrueToDTO());
//
//    }
//
//    @GetMapping("/percent/not-insured")
//    public ResponseEntity<PercentNotInsuredPatientDTO> percentPatientsNotInsured() {
//        return ResponseEntity.ok(patientService.totalPercentNotInsuredPatients());
//    }
//
//    @GetMapping("/percent/insured")
//    public ResponseEntity<PercentNotInsuredPatientDTO> percentPatientsInsured() {
//        return ResponseEntity.ok(patientService.totalPercentInsuredPatients());
//    }

}
