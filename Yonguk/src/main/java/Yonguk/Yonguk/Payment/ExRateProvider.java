package Yonguk.Yonguk.Payment;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;

@Component
public interface ExRateProvider {
    BigDecimal getExeRate(String currency) throws IOException;

}
