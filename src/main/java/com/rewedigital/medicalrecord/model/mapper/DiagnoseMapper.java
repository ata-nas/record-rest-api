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
    DiagnoseDTO toDTO(DiagnoseEntity diagnoseEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseEntity toEntity(CreateDiagnoseDTO diagnoseDTO);

    List<DiagnoseDTO> allToDTO(List<DiagnoseEntity> diagnoseEntities);

}
