package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PricingHistoryRepository extends JpaRepository<PricingHistoryEntity, Long> {

    Optional<PricingHistoryEntity> findByIssueNo(String issueNo);

    @Query(
            "SELECT DISTINCT h FROM PricingHistoryEntity h " +
                    "ORDER BY h.fromDate DESC " +
                    "LIMIT 1"
    )
    Optional<PricingHistoryEntity> findLatestPricing();

    @Query(
            "SELECT DISTINCT h from PricingHistoryEntity h " +
                    "WHERE :date > h.fromDate " +
                    "ORDER BY h.fromDate DESC " +
                    "LIMIT 1"
    )
    Optional<PricingHistoryEntity> findExistingForDate(LocalDate date);

}
