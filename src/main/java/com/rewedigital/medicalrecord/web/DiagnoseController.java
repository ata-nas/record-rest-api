package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.service.DiagnoseService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare/bulgaria/diagnoses")
@RequiredArgsConstructor
public class DiagnoseController {

    private final DiagnoseService diagnoseService;

    @GetMapping
    private ResponseEntity<List<DiagnoseDTO>> diagnose() {
        return ResponseEntity.ok(diagnoseService.getAllDiagnosesToDTO());
    }

    @GetMapping("/{name}")
    private ResponseEntity<DiagnoseDTO> diagnose(
            @PathVariable String name
    ) {
        return ResponseEntity.ok(diagnoseService.getByNameToDTO(name));
    }

    @PostMapping
    private ResponseEntity<DiagnoseDTO> createDiagnose(
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
                .body(diagnoseService.createDiagnose(createDiagnoseDTO));
    }

    @DeleteMapping("/{name}")
    private ResponseEntity<DiagnoseDTO> deleteDiagnose(
            @PathVariable String name
    ) {
        diagnoseService.deleteDiagnoseByName(name);
        return ResponseEntity.noContent().build();
    }

}
