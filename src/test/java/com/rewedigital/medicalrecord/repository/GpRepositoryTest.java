package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class GpRepositoryTest {

    @Autowired
    private GpRepository gpRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void init() {
        initDb();
    }

    @Test
    public void testFindByUic_ReturnsOptionalWithDoctorWithGivenUic(){
        String expected = "0001";

        Optional<GpEntity> optActual = gpRepository.findByUic(expected);
        assertThat(optActual.isPresent()).isTrue();

        GpEntity actual = optActual.get();
        assertThat(actual.getUic()).isEqualTo(expected);
    }

    @Test
    public void testFindByUicAndDeletedFalse_ReturnsOptionalWithDoctorWithGivenUicIfDeletedFalse(){
        String expected = "0001";

        Optional<GpEntity> optActual = gpRepository.findByUicAndDeletedFalse(expected);
        assertThat(optActual.isPresent()).isTrue();

        GpEntity actual = optActual.get();
        assertThat(actual.getUic()).isEqualTo(expected);
    }

    private void initDb() {
        // Init Gp
        GpEntity gp = new GpEntity();
        ReflectionTestUtils.setField(gp, "uic", "0001");
        ReflectionTestUtils.setField(gp, "name", "Petrov");
        ReflectionTestUtils.setField(gp, "birthDate", LocalDate.of(1990, 1, 1));

        testEntityManager.persistAndFlush(gp);
    }

}