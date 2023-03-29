package com.rewedigital.medicalrecord.service;

import com.rewedigital.medicalrecord.model.dto.appointment.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;

import java.util.List;

public interface PricingHistoryService {

    PricingHistoryEntity getPricingByIssueNo(String issueNo);

    List<PricingHistoryEntity> getAllPricing();

    List<PricingHistoryDTO> getAllPricingToDTO();

    PricingHistoryDTO createPricing(CreatePricingHistoryDTO createPricingHistoryDTO);

    PricingHistoryDTO updatePricing(String issueNo, UpdatePricingHistoryDTO updatePricingHistoryDTO);

}
