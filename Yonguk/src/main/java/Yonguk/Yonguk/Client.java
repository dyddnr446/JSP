package Yonguk.Yonguk;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {
public static void main(String[] args) throws IOException {
    PaymentService paymentservice = new PaymentService();
    Payment payment = paymentservice.prepare(100L, "USD", BigDecimal.valueOf(50.7));
    System.out.println(payment);
    }
}
