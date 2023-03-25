package com.rewedigital.medicalrecord.model.mapper;

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

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseDTO diagnoseEntityToDiagnoseDTO(DiagnoseEntity diagnoseEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseEntity diagnoseDTOToDiagnoseEntity(DiagnoseDTO diagnoseDTO);

    List<DiagnoseDTO> allDiagnoseEntityToDiagnoseDTO(List<DiagnoseEntity> diagnoseEntities);

}
