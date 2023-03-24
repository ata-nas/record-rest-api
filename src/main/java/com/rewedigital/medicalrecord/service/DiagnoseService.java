package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;

import java.util.List;

public interface DiagnoseService {

    DiagnoseEntity getByName(String name);
    DiagnoseDTO getByNameToDTO(String name);
    List<DiagnoseEntity> getAllDiagnoses();
    List<DiagnoseDTO> getAllDiagnosesToDTO();
    DiagnoseDTO createDiagnose(CreateDiagnoseDTO diagnoseDTO);
    void deleteDiagnoseByName(String name);

}
