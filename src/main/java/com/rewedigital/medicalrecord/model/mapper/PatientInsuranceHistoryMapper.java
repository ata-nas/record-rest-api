package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.patient.insurance.UpdatePatientInsuranceHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PatientInsuranceHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientInsuranceHistoryMapper {

    PatientInsuranceHistoryEntity toEntity(UpdatePatientInsuranceHistoryDTO updatePatientInsuranceHistoryDTO);

}
