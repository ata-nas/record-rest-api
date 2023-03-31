package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.diagnose.CreateDiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.diagnose.DiagnoseDTO;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseIncomeDTO;
import com.rewedigital.medicalrecord.model.entity.DiagnoseEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;
import com.rewedigital.medicalrecord.model.dto.stats.DiagnoseVisitDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface DiagnoseMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseDTO toDTO(DiagnoseEntity diagnoseEntity);

    DiagnoseVisitDTO toDTO(Long countVisits);
    DiagnoseIncomeDTO toDTO(BigDecimal income);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseDTO toDTO(CreateDiagnoseDTO createDiagnoseDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    DiagnoseEntity toEntity(CreateDiagnoseDTO createDiagnoseDTO);

    Set<DiagnoseDTO> allToDTO(Set<DiagnoseEntity> diagnoseEntities);

}
