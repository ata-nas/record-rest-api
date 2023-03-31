package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.stats.PatientIncomeDTO;
import com.rewedigital.medicalrecord.model.dto.stats.PatientVisitDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;

import org.mapstruct.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperUtil.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface PatientMapper {

    @Mapping(source = "patientEntity.gp.uic", target = "gpUic")
    PatientDTO toDTO(PatientEntity patientEntity);

    UpdatePatientDTO toDTO(CreatePatientDTO createPatientDTO);

    PatientVisitDTO toDTO(Long countVisits);

    PatientIncomeDTO toDTO(BigDecimal income);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "gpUic", target = "gp", qualifiedByName = "findGpByUicCreateUpdate")
    PatientEntity toEntity(CreatePatientDTO createPatientDTO);

    @Mapping(target = "uic", ignore = true)
    @Mapping(source = "updatePatientDTO.gpUic", target = "gp", qualifiedByName = "findGpByUicCreateUpdate")
    PatientEntity toEntity(UpdatePatientDTO updatePatientDTO, @MappingTarget PatientEntity patientEntity);

    Set<PatientDTO> allToDTO(Set<PatientEntity> patientEntity);

}
