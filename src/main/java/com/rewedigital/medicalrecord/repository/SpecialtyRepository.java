package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long> {

    Optional<SpecialtyEntity> findByName(String name);

    Set<SpecialtyEntity> findAllByNameIn(Set<String> names);

}
