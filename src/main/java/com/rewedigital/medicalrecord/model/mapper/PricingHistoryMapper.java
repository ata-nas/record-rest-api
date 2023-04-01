package com.rewedigital.medicalrecord.model.mapper;

import com.rewedigital.medicalrecord.model.dto.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.UpdatePricingHistoryDTO;
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
    @Mapping(target = "issueNo", ignore = true)
    @Mapping(target = "fromDate", ignore = true)
    PricingHistoryEntity toEntity(UpdatePricingHistoryDTO updatePricingHistoryDTO, @MappingTarget PricingHistoryEntity pricingHistoryEntity);

    List<PricingHistoryDTO> allToDTO(List<PricingHistoryEntity> pricingHistoryEntities);

}
