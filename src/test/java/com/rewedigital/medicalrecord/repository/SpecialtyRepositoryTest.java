package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCollection;


@DataJpaTest
class SpecialtyRepositoryTest {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void init() {
        initDb();
    }

    @Test
    public void testFindByName_ReturnsOptionalWithSpecialtyWithGivenName(){
        String expected = "SURGERY";

        Optional<SpecialtyEntity> optActual = specialtyRepository.findByName(expected);
        assertThat(optActual.isPresent()).isTrue();

        SpecialtyEntity actual = optActual.get();
        assertThat(actual.getName()).isEqualTo(expected);
    }

    @Test
    public void testFindByNameAndDeletedFalse_ReturnsOptionalWithSpecialtyWithGivenNameIfDeletedFalse(){
        String expected = "SURGERY";

        Optional<SpecialtyEntity> optActual = specialtyRepository.findByNameAndDeletedFalse(expected);
        assertThat(optActual.isPresent()).isTrue();

        SpecialtyEntity actual = optActual.get();
        assertThat(actual.getName()).isEqualTo(expected);
    }

    @Test
    public void testFindAllByDeletedFalse_ReturnsCollectionContainingSpecialtiesWhereDeletedIsFalse(){
        SpecialtyEntity specialty = new SpecialtyEntity();
        ReflectionTestUtils.setField(specialty, "name", "SURGERY");
        ReflectionTestUtils.setField(specialty, "deleted", Boolean.FALSE);

        Set<SpecialtyEntity> expected = new LinkedHashSet<>();
        expected.add(specialty);

        Set<SpecialtyEntity> actual = specialtyRepository.findAllByDeletedFalse();
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    public void testFindAllByNameInAndDeletedFalse_ReturnsCollectionContainingSpecialtiesWithGivenNamesWhereDeletedIsFalse(){
        SpecialtyEntity specialty = new SpecialtyEntity();
        ReflectionTestUtils.setField(specialty, "name", "SURGERY");
        ReflectionTestUtils.setField(specialty, "deleted", Boolean.FALSE);

        Set<SpecialtyEntity> expected = new LinkedHashSet<>();
        expected.add(specialty);

        Set<SpecialtyEntity> actual = specialtyRepository.findAllByNameInAndDeletedFalse(Set.of(specialty.getName()));
        assertThat(actual.isEmpty()).isFalse();

        assertThatCollection(actual).isEqualTo(expected);
    }

    @Test
    public void testSoftDelete_UpdatesFieldDeletedToFalseOnSpecialtyWithName() {
        specialtyRepository.softDelete("SURGERY");

        Optional<SpecialtyEntity> actual = specialtyRepository.findByName("SURGERY");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().isDeleted()).isTrue();
    }

    @Test
    public void testSoftCreate_UpdatesFieldDeletedToTrueOnSpecialtyWithName() {
        specialtyRepository.softCreate("CARDIOLOGY");

        Optional<SpecialtyEntity> actual = specialtyRepository.findByName("CARDIOLOGY");
        assertThat(actual.isPresent()).isTrue();
        assertThat(actual.get().isDeleted()).isFalse();
    }

    private void initDb() {
        // Init SpecialtyEntity
        SpecialtyEntity specialty = new SpecialtyEntity();
        ReflectionTestUtils.setField(specialty, "name", "SURGERY");
        ReflectionTestUtils.setField(specialty, "deleted", Boolean.FALSE);

        testEntityManager.persistAndFlush(specialty);

        SpecialtyEntity specialtySoftDeleted = new SpecialtyEntity();
        ReflectionTestUtils.setField(specialtySoftDeleted, "name", "CARDIOLOGY");
        ReflectionTestUtils.setField(specialtySoftDeleted, "deleted", Boolean.TRUE);

        testEntityManager.persistAndFlush(specialtySoftDeleted);
    }

}