package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnoseMapper {

    DiagnoseDTO diagnoseEntityToDiagnoseDTO(DiagnoseEntity diagnoseEntity);

    DiagnoseEntity diagnoseDTOToDiagnoseEntity(DiagnoseDTO diagnoseDTO);

    List<DiagnoseDTO> allDiagnoseEntityToDiagnoseDTO (List<DiagnoseEntity> diagnoseEntities);

    List<DiagnoseEntity> allDiagnoseDTOToDiagnoseEntity (List<DiagnoseDTO> diagnoseDTOs);

}
