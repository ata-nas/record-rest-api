package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseIncomeDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseVisitDTO;

import java.util.List;

public interface DiagnoseService {

    DiagnoseEntity getByName(String name);

    DiagnoseDTO getByNameToDTO(String name);

    List<DiagnoseEntity> getAll();

    List<DiagnoseDTO> getAllToDTO();

    DiagnoseDTO create(CreateDiagnoseDTO diagnoseDTO);

    void delete(String name);

    DiagnoseVisitDTO getDiagnoseVisitCount(String name);

    DiagnoseIncomeDTO getDiagnoseIncomeByName(String name);

}
