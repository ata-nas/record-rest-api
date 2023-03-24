package com.rewedigital.medicalrecord.model.mapper.diagnose;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;

import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface DiagnoseMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    CreateDiagnoseDTO diagnoseEntityToCreateDiagnoseDTO(DiagnoseEntity diagnoseEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseEntity createDiagnoseDTOToDiagnoseEntity(CreateDiagnoseDTO diagnoseDTO);

    List<CreateDiagnoseDTO> allDiagnoseEntityToCreateDiagnoseDTO(List<DiagnoseEntity> diagnoseEntities);

    List<DiagnoseEntity> allCreateDiagnoseDTOToDiagnoseEntity(List<CreateDiagnoseDTO> diagnoseDTOs);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseDTO diagnoseEntityToDiagnoseDTO(DiagnoseEntity diagnoseEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseEntity diagnoseDTOToDiagnoseEntity(DiagnoseDTO diagnoseDTO);

    List<DiagnoseDTO> allDiagnoseEntityToDiagnoseDTO(List<DiagnoseEntity> diagnoseEntities);

    List<DiagnoseEntity> allDiagnoseDTOToDiagnoseEntity(List<DiagnoseDTO> diagnoseDTOs);

}
