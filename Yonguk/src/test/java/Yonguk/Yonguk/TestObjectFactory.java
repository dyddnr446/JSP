package Yonguk.Yonguk;

import Yonguk.Yonguk.Payment.ExRateProvider;
import Yonguk.Yonguk.Payment.ExRateProviderStub;
import Yonguk.Yonguk.Payment.PaymentService;
import Yonguk.Yonguk.Payment.WebApiExRateProvider;
import Yonguk.Yonguk.exrate.CachedExRateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class TestObjectFactory {
    @Bean
    public PaymentService paymentService() {
        return new PaymentService(cachedExRateProvider());
    }

    @Bean
    public CachedExRateProvider cachedExRateProvider(){
        return new CachedExRateProvider(exRateProvider());
    }

    @Bean
    public ExRateProvider exRateProvider(){
        return new ExRateProviderStub(BigDecimal.valueOf(1000));
    }


}
