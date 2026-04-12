package Yonguk.Yonguk;

import Yonguk.Yonguk.Payment.Payment;
import Yonguk.Yonguk.Payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
public static void main(String[] args) throws IOException, InterruptedException {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
    PaymentService paymentservice = beanFactory.getBean(PaymentService.class);

    Payment payment = paymentservice.prepare(100L, "USD", BigDecimal.valueOf(50.7));
    System.out.println("payment: " + payment);

    }
}