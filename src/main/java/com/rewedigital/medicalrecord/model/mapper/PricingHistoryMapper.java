package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.healthcare.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PricingHistoryMapper {

    PricingHistoryDTO toDTO(PricingHistoryEntity pricingHistoryEntity);

    @Mapping(target = "id", ignore = true)
    PricingHistoryEntity update(PricingHistoryDTO pricingHistoryDTO, @MappingTarget PricingHistoryEntity pricingHistoryEntity);

}
