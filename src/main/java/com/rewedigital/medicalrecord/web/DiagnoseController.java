package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.exception.diagnose.NoSuchDiagnoseEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.service.DiagnoseService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diagnoses")
@RequiredArgsConstructor
public class DiagnoseController {

    private final DiagnoseService diagnoseService;

    @GetMapping
    private ResponseEntity<List<DiagnoseDTO>> diagnoses() {
        return ResponseEntity.ok(diagnoseService.getAllDiagnosesToDTO());
    }

    @GetMapping("/{id}")
    private ResponseEntity<DiagnoseDTO> diagnoses(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(diagnoseService.getByIdToDTO(id));
    }

    @PostMapping
    private ResponseEntity<DiagnoseDTO> createDiagnose(
            @Valid CreateDiagnoseDTO diagnoseDTO
    ) {
        return ResponseEntity.ok(diagnoseService.createDiagnose(diagnoseDTO));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<CreateDiagnoseDTO> deleteDiagnose(
            @PathVariable Long id
    ) {
        diagnoseService.deleteDiagnoseById(id);
        return ResponseEntity.noContent().build();
    }

}
