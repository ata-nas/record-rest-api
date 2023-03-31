package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingDiagnoseNameValidation;
import com.rewedigital.medicalrecord.service.DiagnoseService;

import jakarta.validation.Valid;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * This @RestController handles CRUD for DiagnoseEntity.
 * There is also custom error annotations present here that stop invalid data to enter the @Service,
 * adding additional layer of security and also stopping invalid data to make potentially very taxing operations.
 */
@RestController
@RequestMapping("/api/healthcare/bulgaria/diagnoses")
@RequiredArgsConstructor
@Validated
public class DiagnoseController {

    private final DiagnoseService diagnoseService;

    @GetMapping("/{name}")
    public ResponseEntity<DiagnoseDTO> diagnose(
            @PathVariable
            @NotBlank
            @ExistingDiagnoseNameValidation(message = "Illegal path! Diagnose with given {name} does not exist!")
            String name
    ) {
        return ResponseEntity.ok(diagnoseService.getByNameToDTO(name));
    }

    @GetMapping
    public ResponseEntity<Set<DiagnoseDTO>> diagnoses() {
        return ResponseEntity.ok(diagnoseService.getAllToDTO());
    }

    @PostMapping
    public ResponseEntity<DiagnoseDTO> createDiagnose(
            @RequestBody @Valid CreateDiagnoseDTO createDiagnoseDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/api")
                                .path("/healthcare")
                                .path("/bulgaria")
                                .path("/diagnoses")
                                .path("/" + createDiagnoseDTO.getName().toLowerCase())
                                .build().toUri()
                )
                .body(diagnoseService.create(createDiagnoseDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<DiagnoseDTO> deleteDiagnose(
            @PathVariable
            @NotBlank
            @ExistingDiagnoseNameValidation(message = "Illegal path! Diagnose with given {name} does not exist!")
            String name
    ) {
        diagnoseService.delete(name);
        return ResponseEntity.noContent().build();
    }

}
