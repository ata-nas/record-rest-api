package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.service.DiagnoseService;

import com.rewedigital.medicalrecord.util.UriBuilderUtil;
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
    private final UriBuilderUtil uriBuilderUtil;

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
            @RequestBody @Valid CreateDiagnoseDTO diagnoseDTO
    ) {
        return ResponseEntity
                .created(uriBuilderUtil.diagnoseCreatedURI(diagnoseDTO.getName()))
                .body(diagnoseService.createDiagnose(diagnoseDTO));
    }

    @DeleteMapping("/{name}")
    private ResponseEntity<CreateDiagnoseDTO> deleteDiagnose(
            @PathVariable String name
    ) {
        diagnoseService.deleteDiagnoseByName(name);
        return ResponseEntity.noContent().build();
    }

}
