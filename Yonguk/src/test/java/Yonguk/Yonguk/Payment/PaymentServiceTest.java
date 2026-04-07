package Yonguk.Yonguk.Payment;


import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;


class PaymentServiceTest {

    @Test
    @DisplayName("prepare 메소드 요구사항 3가지를 잘 충족했는지 검증")
    void prepareTest() throws IOException {
        PaymentService paymentService = new PaymentService(new WebApiExRateProvider());
        Payment payment = paymentService.prepare(1L,"USD", BigDecimal.TEN);

        //환율 정보를 가져온다.
        Assertions.assertThat(payment.getExRate()).isNotNull();
        //원화 환산 금액 계산
        Assertions.assertThat(payment.getConAmount()).
                isEqualTo((payment.getExRate().multiply(payment.getForeignCurrencyAmount())));
        //원화 환산 금액 유효시간 계산
        Assertions.assertThat(payment.getValidUntil()).isAfter(LocalDateTime.now());
    }
}