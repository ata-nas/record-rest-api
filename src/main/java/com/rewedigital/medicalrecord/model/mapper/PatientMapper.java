package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.patient.CreatePatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.PatientDTO;
import com.rewedigital.medicalrecord.model.dto.patient.UpdatePatientDTO;
import com.rewedigital.medicalrecord.model.entity.PatientEntity;
import com.rewedigital.medicalrecord.model.entity.PatientInsuranceHistoryEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;

import jakarta.validation.Valid;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = MapperUtil.class, collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface PatientMapper {

    @Mapping(source = "patientEntity.gp.uic", target = "gpUic")
    PatientDTO toDTO(PatientEntity patientEntity);

    @Mapping(source = "gpUic", target = "gp", qualifiedByName = "findGpByUicCreateUpdate")
    PatientEntity toEntity(CreatePatientDTO createPatientDTO);

    @Mapping(target = "uic", ignore = true)
    @Mapping(source = "updatePatientDTO.gpUic", target = "gp", qualifiedByName = "findGpByUicCreateUpdate")
    PatientEntity toEntity(UpdatePatientDTO updatePatientDTO, @MappingTarget PatientEntity patientEntity);

    List<PatientDTO> allToDTO(List<PatientEntity> patientEntity);

//    @AfterMapping
//    default PatientEntity toEntityUpdate(UpdatePatientDTO updatePatientDTO, PatientEntity patientEntity) {
//        if (updatePatientDTO == null || updatePatientDTO.getInsurances() == null) {
//            return null;
//        }
//        Set<@Valid PatientInsuranceHistoryEntity> toUpdate = patientEntity.getInsurances();
//
//        updatePatientDTO.getInsurances()
//                .stream()
//                .filter(i -> i != null && i.getEndDateTime() != null && i.getStartDateTime() !=null)
//                .map()
//                .forEach(toUpdate::add);
//    }

}
