package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;

import java.util.List;

public interface PricingHistoryService {

    PricingHistoryEntity getByIssueNo(String issueNo);

    List<PricingHistoryEntity> getAll();

    List<PricingHistoryDTO> getAllToDTO();

    PricingHistoryDTO create(CreatePricingHistoryDTO createPricingHistoryDTO);

    PricingHistoryDTO update(String issueNo, UpdatePricingHistoryDTO updatePricingHistoryDTO);

}
