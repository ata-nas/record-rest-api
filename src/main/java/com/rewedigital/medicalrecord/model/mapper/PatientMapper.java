package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface PatientMapper {

    @Mapping(source = "patientEntity.gp.uic", target = "gpUic")
    PatientDTO patientEntityToPatientDTO(PatientEntity patientEntity);

    @Mapping(source = "gpUic", target = "gp", qualifiedByName = "findGpByUicCreateUpdate")
    PatientEntity createPatientDTOToPatientEntity(CreatePatientDTO createPatientDTO);

    @Mapping(source = "updatePatientDTO.gpUic", target = "gp", qualifiedByName = "findGpByUicCreateUpdate")
    @Mapping(target = "id", source = "uic", qualifiedByName = "findPatientIdByUic")
    @Mapping(target = "uic", source = "uic")
    PatientEntity updatePatientDTOToPatientEntity(String uic, UpdatePatientDTO updatePatientDTO);

    List<PatientDTO> allPatientEntityToPatientDTO(List<PatientEntity> patientEntity);

}
