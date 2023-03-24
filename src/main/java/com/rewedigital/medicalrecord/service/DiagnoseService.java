package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;

import java.util.List;
import java.util.Optional;

public interface DiagnoseService {

    DiagnoseEntity getById(Long id);
    DiagnoseDTO getByIdToDTO(Long id);
    List<DiagnoseEntity> getAllDiagnoses();
    List<DiagnoseDTO> getAllDiagnosesToDTO();
    DiagnoseDTO createDiagnose(CreateDiagnoseDTO diagnoseDTO);
    void deleteDiagnoseById(Long id);

}
