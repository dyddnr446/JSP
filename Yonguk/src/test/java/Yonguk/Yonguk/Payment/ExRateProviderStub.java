package Yonguk.Yonguk.Payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.time.LocalDateTime;

public class ExRateProviderStub implements ExRateProvider{
    private final BigDecimal exRate;
    public BigDecimal getExRate() {
        return exRate;
    }
    public BigDecimal setExRate() {
        return exRate;
    }

    public ExRateProviderStub(BigDecimal exRate) {
        this.exRate = exRate;
    }

    @Override
    public BigDecimal getExeRate(String currency) throws IOException {
        return exRate;
    }
}
