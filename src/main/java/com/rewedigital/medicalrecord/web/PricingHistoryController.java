package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.validation.ExistingPricingHistoryIssueNoValidation;
import com.rewedigital.medicalrecord.service.PricingHistoryService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * This @RestController handles CRUD for PricingHistoryEntity.
 * There is also custom error annotations present here that stop invalid data to enter the @Service,
 * adding additional layer of security and also stopping invalid data to make potentially very taxing operations.
 */
@RestController
@RequestMapping("/api/healthcare/bulgaria/pricing")
@RequiredArgsConstructor
@Validated
public class PricingHistoryController {

    private final PricingHistoryService pricingHistoryService;


    @GetMapping
    public ResponseEntity<List<PricingHistoryDTO>> pricingHistory() {
        return ResponseEntity.ok(pricingHistoryService.getAllPricingToDTO());
    }

    @PostMapping
    public ResponseEntity<PricingHistoryDTO> createPricing(
            @RequestBody @Valid CreatePricingHistoryDTO createPricingHistoryDTO,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        return ResponseEntity
                .created(
                        uriComponentsBuilder
                                .path("/api")
                                .path("/healthcare")
                                .path("/bulgaria")
                                .path("/pricing")
                                .path("/" + createPricingHistoryDTO.getIssueNo())
                                .build().toUri()
                )
                .body(pricingHistoryService.createPricing(createPricingHistoryDTO));
    }

    @PutMapping("/{issueNo}")
    public ResponseEntity<PricingHistoryDTO> updatePricing(
            @NotBlank
            @ExistingPricingHistoryIssueNoValidation
            @PathVariable
            String issueNo,
            @RequestBody @Valid UpdatePricingHistoryDTO updatePricingHistoryDTO
    ) {
        return ResponseEntity.ok(pricingHistoryService.updatePricing(issueNo, updatePricingHistoryDTO));
    }

}
