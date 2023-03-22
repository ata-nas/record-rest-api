package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;

import java.util.List;

public interface DiagnoseService {

    List<DiagnoseEntity> getAllDiagnoses();
    List<DiagnoseDTO> getAllDiagnosesToDTO();
    DiagnoseDTO createDiagnose(DiagnoseDTO diagnoseDTO);

}
