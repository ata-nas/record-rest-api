package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.DiagnoseDTO;
import com.rewedigital.medicalrecord.service.DiagnoseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diagnoses")
@RequiredArgsConstructor
public class DiagnoseController {

    private final DiagnoseService diagnoseService;

    @GetMapping
    private List<DiagnoseDTO> diagnoses() {
        return diagnoseService.getAllDiagnosesToDTO();
    }

    @PostMapping
    private DiagnoseDTO createDiagnose(DiagnoseDTO diagnoseDTO) {
        return diagnoseService.createDiagnose(diagnoseDTO);
    }

}
