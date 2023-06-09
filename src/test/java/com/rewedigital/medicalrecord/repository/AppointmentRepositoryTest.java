package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


@DataJpaTest
class AppointmentRepositoryTest {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void init() {
        initDb();
    }

    @Test
    public void testFindByUic_ReturnsOptionalWithAppointmentByGivenUic() {
        String expected = "0001";

        Optional<AppointmentEntity> optActual = appointmentRepository.findByUic(expected);
        assertThat(optActual.isPresent()).isTrue();

        AppointmentEntity actual = optActual.get();
        assertThat(actual.getUic()).isEqualTo(expected);
    }

    @Test
    public void testGetTotalIncome_ReturnsTotalIncomeOfAllVisits() {
        double expected = BigDecimal.TEN.doubleValue();

        double actual = appointmentRepository.getTotalIncome().doubleValue();

        assertThat(actual).isEqualTo(expected);
    }

    private void initDb() {
        // Init Doctors
        DoctorEntity doctor = new DoctorEntity();
        ReflectionTestUtils.setField(doctor, "uic", "0001");
        ReflectionTestUtils.setField(doctor, "name", "Georgiev");
        ReflectionTestUtils.setField(doctor, "birthDate", LocalDate.of(1990, 1, 1));
        ReflectionTestUtils.setField(doctor, "deleted", Boolean.FALSE);

        DoctorEntity doctorEntity = testEntityManager.persistAndFlush(doctor);

        DoctorEntity doctorSoftDeleted = new DoctorEntity();
        ReflectionTestUtils.setField(doctorSoftDeleted, "uic", "0002");
        ReflectionTestUtils.setField(doctorSoftDeleted, "name", "Dimitrov");
        ReflectionTestUtils.setField(doctorSoftDeleted, "birthDate", LocalDate.of(1990, 1, 1));
        ReflectionTestUtils.setField(doctorSoftDeleted, "deleted", Boolean.TRUE);

        testEntityManager.persistAndFlush(doctorSoftDeleted);

        // Init Patients
        PatientEntity patient = new PatientEntity();
        ReflectionTestUtils.setField(patient, "uic", "000001");
        ReflectionTestUtils.setField(patient, "name", "Petar");
        ReflectionTestUtils.setField(patient, "gp", null);
        ReflectionTestUtils.setField(patient, "deleted", Boolean.FALSE);

        PatientEntity patientEntity = testEntityManager.persistAndFlush(patient);

        PatientEntity patientSoftDeleted = new PatientEntity();
        ReflectionTestUtils.setField(patientSoftDeleted, "uic", "000002");
        ReflectionTestUtils.setField(patientSoftDeleted, "name", "Ivan");
        ReflectionTestUtils.setField(patientSoftDeleted, "gp", null);
        ReflectionTestUtils.setField(patientSoftDeleted, "deleted", Boolean.TRUE);

        testEntityManager.persistAndFlush(patientSoftDeleted);

        // Init Diagnoses
        DiagnoseEntity diagnose = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnose, "name", "HEALTHY");
        ReflectionTestUtils.setField(diagnose, "deleted", Boolean.FALSE);

        DiagnoseEntity diagnoseEntity = testEntityManager.persistAndFlush(diagnose);

        DiagnoseEntity diagnoseSoftDeleted = new DiagnoseEntity();
        ReflectionTestUtils.setField(diagnoseSoftDeleted, "name", "FLU");
        ReflectionTestUtils.setField(diagnoseSoftDeleted, "deleted", Boolean.TRUE);

        testEntityManager.persistAndFlush(diagnoseSoftDeleted);

        // Init PricingHistory
        PricingHistoryEntity pricing = new PricingHistoryEntity();
        ReflectionTestUtils.setField(pricing,"issueNo", "1");
        ReflectionTestUtils.setField(pricing,"appointmentFees", BigDecimal.TEN);
        ReflectionTestUtils.setField(pricing,"fromDate", LocalDate.of(1990, 1, 1));

        PricingHistoryEntity pricingHistoryEntity = testEntityManager.persistAndFlush(pricing);

        // Init Appointments
        LinkedHashSet<DiagnoseEntity> diagnoses = new LinkedHashSet<>();
        diagnoses.add(diagnoseEntity);

        AppointmentEntity appointment = new AppointmentEntity();
        ReflectionTestUtils.setField(appointment, "uic", "0001");
        ReflectionTestUtils.setField(appointment, "patient", patientEntity);
        ReflectionTestUtils.setField(appointment, "price", pricingHistoryEntity);
        ReflectionTestUtils.setField(appointment, "description", "descr");
        ReflectionTestUtils.setField(appointment, "doctor", doctorEntity);
        ReflectionTestUtils.setField(appointment, "date", LocalDate.of(2020, 1, 1));
        ReflectionTestUtils.setField(appointment, "diagnoses", diagnoses);

        testEntityManager.persistAndFlush(appointment);
    }

}
