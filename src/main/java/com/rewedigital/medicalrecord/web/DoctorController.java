package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.UpdateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.exception.BindExceptionDTO;
import com.rewedigital.medicalrecord.model.mapper.FieldErrorMapper;
import com.rewedigital.medicalrecord.model.validation.ExistingDoctorUicValidation;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare/bulgaria/doctors")
@RequiredArgsConstructor
@Validated
public class DoctorController {

    // post mapping /doctors/gp/{uic} will make the existing doctor (if exist) into gp
    // delete mapping /doctors/gp/{uic} will remove the existing gp entry from gp table (if exist)
    // I will control all doctor and gp from here, they are connected through inheritance I will use path variable
    // to differentiate which I want to access. I will have a Doctor manager orchestration to handle operations.

    // TODO make custom exception handler here when creating a doctor, for NoSuchSpecialtyEntityFoundException (or Binding, need to check), to return 409 conflict!

    @GetMapping("/{uic}")
    public ResponseEntity<DoctorDTO> doctor(
            @PathVariable
            @NotBlank
            @ExistingDoctorUicValidation(message = "Invalid path! Doctor with given {uic} does not exist!")
            String uic
    ) {
        return null;
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> doctors() {
        return null;
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(
            @RequestBody @Valid CreateDoctorDTO createDoctorDTO
    ) {
        return null;
    }

    @PutMapping("/{uic}")
    public ResponseEntity<DoctorDTO> updateDoctor(
            @PathVariable
            @NotBlank
            @ExistingDoctorUicValidation(message = "Invalid path! Doctor with given {uic} does not exist!")
            String uic,
            @RequestBody @Valid UpdateDoctorDTO updateDoctorDTO
    ) {
        return null;
    }

    @DeleteMapping("/{uic}")
    public ResponseEntity<DoctorDTO> deleteDoctor(
            @PathVariable
            @NotBlank
            @ExistingDoctorUicValidation(message = "Invalid path! Doctor with given {uic} does not exist!")
            String uic,
            @RequestBody @Valid CreateDoctorDTO createDoctorDTO
    ) {
        return null;
    }

    @GetMapping("/gp")
    public ResponseEntity<List<DoctorDTO>> doctorsGp() {
        return null;
    }

}
