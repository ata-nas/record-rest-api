package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.specialty.CreateSpecialtyDTO;
import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingSpecialtyNameValidation;
import com.rewedigital.medicalrecord.service.SpecialtyService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

/**
 * This @RestController handles CRUD for SpecialtyEntity.
 * There is also custom error annotations present here that stop invalid data to enter the @Service,
 * adding additional layer of security and also stopping invalid data to make potentially very taxing operations.
 */
@RestController
@RequestMapping("/api/healthcare/bulgaria/specialties")
@RequiredArgsConstructor
@Validated
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping("/{name}")
    public ResponseEntity<SpecialtyDTO> specialty(
            @PathVariable
            @NotBlank
            @ExistingSpecialtyNameValidation(message = "Illegal path! Specialty with given {name} does not exist!")
            String name
    ) {
        return ResponseEntity.ok(specialtyService.getByNameToDTO(name));
    }

    @GetMapping
    public ResponseEntity<Set<SpecialtyDTO>> specialty() {
        return ResponseEntity.ok(specialtyService.getAllToDTO());
    }

    @PostMapping
    public ResponseEntity<SpecialtyDTO> createSpecialty(
            @RequestBody @Valid CreateSpecialtyDTO createSpecialtyDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/api")
                                .path("/healthcare")
                                .path("/bulgaria")
                                .path("/specialties")
                                .path("/" + createSpecialtyDTO.getName().toLowerCase())
                                .build().toUri()
                )
                .body(specialtyService.create(createSpecialtyDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<SpecialtyDTO> deleteSpecialty(
            @PathVariable
            @NotBlank
            @ExistingSpecialtyNameValidation(message = "Illegal path! Specialty with given {name} does not exist!")
            String name
    ) {
        specialtyService.delete(name);
        return ResponseEntity.noContent().build();
    }

}
