package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.UpdateDoctorDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingDoctorUicValidation;

import com.rewedigital.medicalrecord.service.DoctorService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare/bulgaria/doctors")
@RequiredArgsConstructor
@Validated
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping("/{uic}")
    public ResponseEntity<DoctorDTO> doctor(
            @PathVariable
            @NotBlank
            @ExistingDoctorUicValidation(message = "Invalid path! Doctor with given {uic} does not exist!")
            String uic
    ) {
        return ResponseEntity.ok(doctorService.getByUicToDTO(uic));
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> doctors() {
        return ResponseEntity.ok(doctorService.getAllToDTO());
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(
            @RequestBody @Valid CreateDoctorDTO createDoctorDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/api")
                                .path("/healthcare")
                                .path("/bulgaria")
                                .path("/doctors")
                                .path("/" + createDoctorDTO.getUic())
                                .build().toUri()
                )
                .body(doctorService.create(createDoctorDTO));
    }

    @PutMapping("/{uic}")
    public ResponseEntity<DoctorDTO> updateDoctor(
            @PathVariable
            @NotBlank
            @ExistingDoctorUicValidation(message = "Invalid path! Doctor with given {uic} does not exist!")
            String uic,
            @RequestBody @Valid UpdateDoctorDTO updateDoctorDTO
    ) {
        return ResponseEntity.ok(doctorService.update(uic, updateDoctorDTO));
    }

    @DeleteMapping("/{uic}")
    public ResponseEntity<DoctorDTO> deleteDoctor(
            @PathVariable
            @NotBlank
            @ExistingDoctorUicValidation(message = "Invalid path! Doctor with given {uic} does not exist!")
            String uic
    ) {
        doctorService.delete(uic);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/gp")
    public ResponseEntity<List<DoctorDTO>> doctorsGp() {
        return null;
    }

}
