package Yonguk.Yonguk.Payment;


import Yonguk.Yonguk.ObjectFactory;
import Yonguk.Yonguk.TestObjectFactory;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.math.BigDecimal;
import static java.math.BigDecimal.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestObjectFactory.class)
class PaymentServiceTest {
    @Autowired PaymentService paymentService;

    @Test
    @DisplayName("prepare 메소드 요구사항 3가지를 잘 충족했는지 검증")
    void convertedAmount() throws IOException {
        Payment payment = paymentService.prepare(1L, "USD", TEN);

        testAmount(valueOf(500), valueOf(5000));
        testAmount(valueOf(400), valueOf(4000));

    }
    private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
        PaymentService paymentService = new PaymentService(new ExRateProviderStub(exRate));

        Payment payment = paymentService.prepare(1L,"USD", TEN);

        //환율 정보를 가져온다.
        assertThat(payment.getExRate()).isEqualByComparingTo(exRate);
        assertThat(payment.getConAmount()).isEqualTo(convertedAmount);
    }
}
