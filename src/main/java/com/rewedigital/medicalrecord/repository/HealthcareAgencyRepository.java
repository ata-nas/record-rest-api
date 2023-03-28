package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.HealthcareAgencyEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HealthcareAgencyRepository extends JpaRepository<HealthcareAgencyEntity, Long> {

    Optional<HealthcareAgencyEntity> findByCountry(String country);

}
