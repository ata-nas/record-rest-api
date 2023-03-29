package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.SpecialtyEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface SpecialtyRepository extends JpaRepository<SpecialtyEntity, Long> {

    Optional<SpecialtyEntity> findByName(String name);

    Optional<SpecialtyEntity> findByNameAndDeletedFalse(String name);

    @Query(
            "SELECT s FROM SpecialtyEntity s " +
                    "WHERE s.deleted = false"
    )
    List<SpecialtyEntity> findAllDeletedFalse();

    Set<SpecialtyEntity> findAllByNameIn(Set<String> names);

    @Query("UPDATE SpecialtyEntity s SET s.deleted = true WHERE s.name = :name")
    @Modifying
    void softDelete(String name);

    @Query("UPDATE SpecialtyEntity s SET s.deleted = false WHERE s.name = :name")
    @Modifying
    void softCreate(String name);

}
