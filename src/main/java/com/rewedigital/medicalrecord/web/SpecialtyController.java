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

import java.util.List;

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
    public ResponseEntity<List<SpecialtyDTO>> specialty() {
        return ResponseEntity.ok(specialtyService.getAllSpecialtiesToDTO());
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
                .body(specialtyService.createSpecialty(createSpecialtyDTO));
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<SpecialtyDTO> deleteSpecialty(
            @PathVariable
            @NotBlank
            @ExistingSpecialtyNameValidation(message = "Illegal path! Specialty with given {name} does not exist!")
            String name
    ) {
        specialtyService.deleteSpecialtyByName(name);
        return ResponseEntity.noContent().build();
    }

}
