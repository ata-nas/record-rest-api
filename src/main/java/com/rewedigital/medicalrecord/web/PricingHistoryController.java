package com.rewedigital.medicalrecord.web;

import com.rewedigital.medicalrecord.model.dto.appointment.pricing.CreatePricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.PricingHistoryDTO;
import com.rewedigital.medicalrecord.model.dto.appointment.pricing.UpdatePricingHistoryDTO;
import com.rewedigital.medicalrecord.service.PricingHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/healthcare/bulgaria/appointments/pricing")
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
                                .path("/appointments")
                                .path("/pricing")
                                .path("/" + createPricingHistoryDTO.getIssueNo())
                                .build().toUri()
                )
                .body(pricingHistoryService.createPricing(createPricingHistoryDTO));
    }

    @PutMapping("/{issueNo}")
    public ResponseEntity<PricingHistoryDTO> updatePricing(
            @PathVariable String issueNo,
            @RequestBody UpdatePricingHistoryDTO updatePricingHistoryDTO
    ) {
        return ResponseEntity.ok(pricingHistoryService.updatePricing(issueNo, updatePricingHistoryDTO));
    }

}
