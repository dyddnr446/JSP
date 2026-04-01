package Yonguk.Yonguk;

import java.io.IOException;
import java.math.BigDecimal;

public class SimpleExRate {
    BigDecimal getKRWExeRate(String currency) throws IOException {
        if(currency.equals("USD")) return BigDecimal.valueOf(1000);
        throw new IllegalArgumentException("지원되지 않는 통화입니다");
    }
}
