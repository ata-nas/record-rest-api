package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.specialty.CreateSpecialtyDTO;
import com.rewedigital.medicalrecord.model.dto.specialty.SpecialtyDTO;
import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;
import com.rewedigital.medicalrecord.model.mapper.util.MapperUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = MapperUtil.class)
public interface SpecialtyMapper {

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    SpecialtyDTO toDTO(SpecialtyEntity specialtyEntity);

    @Mapping(source = "name", target = "name", qualifiedByName = "toUpper")
    SpecialtyEntity toEntity(CreateSpecialtyDTO createSpecialtyDTO);

    List<SpecialtyDTO> allToDTO(List<SpecialtyEntity> specialtyEntity);

}
