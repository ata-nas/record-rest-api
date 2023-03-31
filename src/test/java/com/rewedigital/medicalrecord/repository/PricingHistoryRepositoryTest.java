package com.rewedigital.medicalrecord.repository;

import com.rewedigital.medicalrecord.model.entity.PricingHistoryEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class PricingHistoryRepositoryTest {

    @Autowired
    private PricingHistoryRepository pricingHistoryRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    public void init() {
        initDb();
    }

    @Test
    public void testFindByIssueNo_ReturnsOptionalWithPricingHistoryWithIssueNo() {
        String expected = "1";

        Optional<PricingHistoryEntity> actual = pricingHistoryRepository.findByIssueNo(expected);
        assertThat(actual.isPresent()).isTrue();

        assertThat(actual.get().getIssueNo()).isEqualTo(expected);
    }

    @Test
    public void testFindLatestPricing_ReturnsOptionalWithLatestIssuedPricingHistory() {
        String expected = "1";

        Optional<PricingHistoryEntity> actual = pricingHistoryRepository.findLatestPricing();
        assertThat(actual.isPresent()).isTrue();

        assertThat(actual.get().getIssueNo()).isEqualTo(expected);
    }

    @Test
    public void testFindExistingForDate_ReturnsOptionalWithPriceHistoryThatIsTheCorrectOneForGivenDate() {
        String expected = "1";

        Optional<PricingHistoryEntity> actual = pricingHistoryRepository.findExistingForDate(LocalDate.of(2020, 1, 1));
        assertThat(actual.isPresent()).isTrue();

        assertThat(actual.get().getIssueNo()).isEqualTo(expected);
    }

    private void initDb() {
        // Init PricingHistory
        PricingHistoryEntity pricing = new PricingHistoryEntity();
        ReflectionTestUtils.setField(pricing,"issueNo", "1");
        ReflectionTestUtils.setField(pricing,"appointmentFees", BigDecimal.TEN);
        ReflectionTestUtils.setField(pricing,"fromDate", LocalDate.of(1990, 1, 1));

        testEntityManager.persistAndFlush(pricing);
    }

}
