package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.appointment.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PricingHistoryMapper {

    PricingHistoryDTO toDTO(PricingHistoryEntity pricingHistoryEntity);

    PricingHistoryEntity toEntity(CreatePricingHistoryDTO pricingHistoryDTO);

    @Mapping(target = "id", ignore = true)
    PricingHistoryEntity update(UpdatePricingHistoryDTO updatePricingHistoryDTO, @MappingTarget PricingHistoryEntity pricingHistoryEntity);

    List<PricingHistoryDTO> allToDTO(List<PricingHistoryEntity> pricingHistoryEntities);

}
