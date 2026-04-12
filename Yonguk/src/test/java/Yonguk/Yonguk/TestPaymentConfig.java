package Yonguk.Yonguk;

import Yonguk.Yonguk.Payment.ExRateProvider;
import Yonguk.Yonguk.Payment.ExRateProviderStub;
import Yonguk.Yonguk.Payment.PaymentService;
import Yonguk.Yonguk.exrate.CachedExRateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
public class TestPaymentConfig {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider(), clock());
    }

    @Bean
    public CachedExRateProvider cachedExRateProvider(){
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(BigDecimal.valueOf(1000));
    }

    @Bean
    public Clock clock(){return Clock.fixed(Instant.now(), ZoneId.systemDefault());}
}