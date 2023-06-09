package com.rewedigital.medicalrecord.service.impl;

import com.rewedigital.medicalrecord.exception.notfound.NoSuchPricingHistoryEntityFoundException;
import com.rewedigital.medicalrecord.model.dto.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;
import com.rewedigital.medicalrecord.model.mapper.PricingHistoryMapper;
import com.rewedigital.medicalrecord.repository.PricingHistoryRepository;
import com.rewedigital.medicalrecord.service.PricingHistoryService;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PricingHistoryServiceImpl implements PricingHistoryService {

    private final PricingHistoryRepository pricingHistoryRepository;
    private final PricingHistoryMapper pricingHistoryMapper;

    @Override
    public PricingHistoryEntity getByIssueNo(String issueNo) {
        return pricingHistoryRepository.findByIssueNo(issueNo)
                .orElseThrow(() -> new NoSuchPricingHistoryEntityFoundException("issueNo", issueNo));
    }

    @Override
    public List<PricingHistoryEntity> getAll() {
        List<PricingHistoryEntity> all = pricingHistoryRepository.findAll();
        if (all.isEmpty()) {
            throw new NoSuchPricingHistoryEntityFoundException("No Pricing History found!");
        }
        return all;
    }

    @Override
    public List<PricingHistoryDTO> getAllToDTO() {
        return pricingHistoryMapper.allToDTO(getAll());
    }

    @Override
    public PricingHistoryDTO create(CreatePricingHistoryDTO createPricingHistoryDTO) {
        return pricingHistoryMapper.toDTO(
                pricingHistoryRepository.save(
                        pricingHistoryMapper.toEntity(createPricingHistoryDTO)
                )
        );
    }

    @Override
    public PricingHistoryDTO update(String issueNo, UpdatePricingHistoryDTO updatePricingHistoryDTO) {
        return pricingHistoryMapper.toDTO(
                pricingHistoryRepository.save(
                        pricingHistoryMapper.toEntity(updatePricingHistoryDTO, getByIssueNo(issueNo))
                )
        );
    }

}
