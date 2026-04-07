package Yonguk.Yonguk.exrate;

import Yonguk.Yonguk.Payment.ExRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CachedExRateProvider implements ExRateProvider {
    private final ExRateProvider target;

    private BigDecimal cachedExRate;
    private LocalDateTime cacheExpiaryTime;

    public CachedExRateProvider(ExRateProvider target) {
        this.target = target;
    }

    @Override
    public BigDecimal getExeRate(String currency) throws IOException {
        if(cachedExRate == null || cacheExpiaryTime.isBefore(LocalDateTime.now())){
            cachedExRate = this.target.getExeRate(currency);
            System.out.println("Cached Updated");
            cacheExpiaryTime = LocalDateTime.now().plusSeconds(3);
        }
        return this.cachedExRate;
    }
}
