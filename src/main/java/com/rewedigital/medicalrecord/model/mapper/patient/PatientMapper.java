package com.rewedigital.medicalrecord.model.mapper.patient;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface PatientMapper {

    @Mapping(source = "patientEntity.gp.uic", target = "gpUic")
    PatientDTO patientEntityToPatientDTO(PatientEntity patientEntity);

    @Mapping(source = "gpUic", target = "gp", qualifiedByName = "findGpByUicCreate")
    PatientEntity createPatientDTOToPatientEntity(CreatePatientDTO createPatientDTO);

    List<PatientDTO> allPatientEntityToPatientDTO(List<PatientEntity> patientEntity);

}
