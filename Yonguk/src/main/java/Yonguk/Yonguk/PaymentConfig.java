package Yonguk.Yonguk;

import Yonguk.Yonguk.Payment.ExRateProvider;
import Yonguk.Yonguk.Payment.PaymentService;
import Yonguk.Yonguk.Payment.WebApiExRateProvider;
import Yonguk.Yonguk.exrate.CachedExRateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class PaymentConfig {
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
        return new WebApiExRateProvider();
    }

    @Bean
    public Clock clock(){return Clock.systemDefaultZone();}

}
