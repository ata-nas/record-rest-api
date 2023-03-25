package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.GpEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GpRepository extends JpaRepository<GpEntity, Long> {
}
