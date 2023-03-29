package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.appointment.AppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.CreateAppointmentDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.UpdateAppointmentDTO;
import com.rewedigital.medicalrecord.model.entity.AppointmentEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface AppointmentMapper {

    @Mapping(target = "patientUic", source = "patient.uic")
    @Mapping(target = "doctorUic", source = "doctor.uic")
    AppointmentDTO toDTO(AppointmentEntity appointmentEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patient", source = "patientUic", qualifiedByName = "findPatientByUicCreateUpdate")
    @Mapping(target = "doctor", source = "doctorUic", qualifiedByName = "findDoctorByUicCreateUpdate")
    @Mapping(target = "price", source = "date", qualifiedByName = "findPricingHistoryInDate")
    @Mapping(target = "diagnoses", source = "diagnoses", qualifiedByName = "findAllDiagnosesByNameCreateUpdate")
    AppointmentEntity toEntity(CreateAppointmentDTO createAppointmentDTO);

    @Mapping(target = "uic", ignore = true)
    @Mapping(target = "patient", source = "patientUic", qualifiedByName = "findPatientByUicCreateUpdate")
    @Mapping(target = "doctor", source = "doctorUic", qualifiedByName = "findDoctorByUicCreateUpdate")
    @Mapping(target = "price", source = "date", qualifiedByName = "findPricingHistoryInDate")
    @Mapping(target = "diagnoses", source = "diagnoses", qualifiedByName = "findAllDiagnosesByNameCreateUpdate")
    AppointmentEntity toEntity(UpdateAppointmentDTO updateAppointmentDTO, @MappingTarget AppointmentEntity appointmentEntity);

    List<AppointmentDTO> allToDTO(List<AppointmentEntity> appointments);

}
