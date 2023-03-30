package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.appointment.AppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.CreateAppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.UpdateAppointmentDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingAppointmentUicValidation;
import com.rewedigital.medicalrecord.service.AppointmentService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * This @RestController handles CRUD for AppointmentEntity.
 * There is also custom error annotations present here that stop invalid data to enter the @Service,
 * adding additional layer of security and also stopping invalid data to make potentially very taxing operations.
 */
@RestController
@RequestMapping("/api/healthcare/bulgaria/appointments")
@RequiredArgsConstructor
@Validated
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/{uic}")
    public ResponseEntity<AppointmentDTO> appointment(
            @NotBlank
            @ExistingAppointmentUicValidation
            @PathVariable
            String uic
    ) {
        return ResponseEntity.ok(appointmentService.getByUicToDTO(uic));
    }

    @GetMapping
    public ResponseEntity<List<AppointmentDTO>> appointments() {
        return ResponseEntity.ok(appointmentService.getAllToDTO());
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> create(
            @RequestBody @Valid CreateAppointmentDTO createAppointmentDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/api")
                                .path("/healthcare")
                                .path("/bulgaria")
                                .path("/appointments")
                                .path("/" + createAppointmentDTO.getUic())
                                .build().toUri()
                )
                .body(appointmentService.create(createAppointmentDTO));
    }

    @PutMapping("/{uic}")
    public ResponseEntity<AppointmentDTO> update(
            @PathVariable
            @NotBlank
            @ExistingAppointmentUicValidation(message = "Invalid path! Appointment with given {uic} does not exist!")
            String uic,
            @RequestBody @Valid UpdateAppointmentDTO updateAppointmentDTO
    ) {
        return ResponseEntity.ok(appointmentService.update(uic, updateAppointmentDTO));
    }

    @DeleteMapping("/{uic}")
    public ResponseEntity<AppointmentDTO> delete(
            @PathVariable
            @NotBlank
            @ExistingAppointmentUicValidation(message = "Invalid path! Appointment with given {uic} does not exist!")
            String uic
    ) {
        appointmentService.delete(uic);
        return ResponseEntity.noContent().build();
    }

}
