package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.doctor.CreateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.DoctorDTO;
import com.rewedigital.medicalrecord.model.dto.doctor.UpdateDoctorDTO;
import com.rewedigital.medicalrecord.model.dto.stats.CountDoctorIncomeHigherThanDTO;
import com.rewedigital.medicalrecord.model.entity.DoctorEntity;
import com.rewedigital.medicalrecord.model.entity.GpEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface DoctorMapper {

    @Mapping(target = "isGp", source = "doctorEntity.id", qualifiedByName = "findGpByIdCheckTrue")
    DoctorDTO toDTO(DoctorEntity doctorEntity);

    UpdateDoctorDTO toDTO(CreateDoctorDTO createDoctorDTO);

    @Mapping(target = "countDoctorsHigherIncomeThanGiven", source = "result")
    CountDoctorIncomeHigherThanDTO toDTO(Long result);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    DoctorEntity toEntity(CreateDoctorDTO createDoctorDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uic", ignore = true)
    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    DoctorEntity toEntity(UpdateDoctorDTO updateDoctorDTO, @MappingTarget DoctorEntity doctorEntity);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    GpEntity toEntityGp(CreateDoctorDTO createDoctorDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "uic", ignore = true)
    @Mapping(source = "specialtiesNames", target = "specialties", qualifiedByName = "findAllSpecialtiesByNameCreateUpdate")
    GpEntity toEntityGp(UpdateDoctorDTO updateDoctorDTO, @MappingTarget GpEntity gpEntity);

    List<DoctorDTO> allToDTO(List<DoctorEntity> doctorEntities);

}
