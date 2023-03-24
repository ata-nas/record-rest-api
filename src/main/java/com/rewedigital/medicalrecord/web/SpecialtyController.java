package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.specialty.CreateSpecialtyDTO;
import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
import com.rewedigital.medicalrecord.service.SpecialtyService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare/bulgaria/specialties")
@RequiredArgsConstructor
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    @GetMapping
    private ResponseEntity<List<SpecialtyDTO>> specialty() {
        return ResponseEntity.ok(specialtyService.getAllSpecialtiesToDTO());
    }

    @GetMapping("/{name}")
    private ResponseEntity<SpecialtyDTO> specialty(
            @PathVariable String name
    ) {
        return ResponseEntity.ok(specialtyService.getByNameToDTO(name));
    }

    @PostMapping
    private ResponseEntity<SpecialtyDTO> createSpecialty(
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
    private ResponseEntity<SpecialtyDTO> deleteSpecialty(
            @PathVariable String name
    ) {
        specialtyService.deleteSpecialtyByName(name);
        return ResponseEntity.noContent().build();
    }

}
