package Yonguk.Yonguk.Payment;


import Yonguk.Yonguk.TestPaymentConfig;
import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static java.math.BigDecimal.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceTest {
    @Autowired PaymentService paymentService;
    Clock clock;

    @BeforeEach
    void beforEach() {
        this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    }



    @Test
    @DisplayName("prepare 메소드 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        testAmount(valueOf(500), valueOf(5000), this.clock);
        testAmount(valueOf(400), valueOf(4000), this.clock);
    }

    @Test
    void validUntil() throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(valueOf(1000)), clock);
        Payment payment = paymentService.prepare(1L,"USD", TEN);
        //valid until이 30분 뒤로 설정 되었는가?
        LocalDateTime now = LocalDateTime.now(this.clock);
        LocalDateTime expectedValidUntil = now.plusMinutes(30);
        Assertions.assertThat(payment.getValidUntil()).isEqualTo(expectedValidUntil);
    }


    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) throws IOException {

        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate), clock);
        Payment payment = paymentService.prepare(1L,"USD", TEN);

        //환율 정보를 가져온다.
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConAmount()).isEqualTo(convertedAmount);
    }
}
