package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PricingHistoryRepository extends JpaRepository<PricingHistoryEntity, Long> {

    @Query(
            "SELECT DISTINCT h FROM PricingHistoryEntity h " +
                    "WHERE :startDate BETWEEN h.startDate AND h.endDate OR h.startDate BETWEEN :startDate AND :endDate"
    )
    Optional<PricingHistoryEntity> findConflictingDates(LocalDate startDate, LocalDate endDate);

}
