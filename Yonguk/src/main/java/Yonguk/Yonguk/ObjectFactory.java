package Yonguk.Yonguk;

import Yonguk.Yonguk.Payment.ExRateProvider;
import Yonguk.Yonguk.Payment.PaymentService;
import Yonguk.Yonguk.Payment.WebApiExRateProvider;
import Yonguk.Yonguk.exrate.CachedExRateProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactory {
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
        return new WebApiExRateProvider();
    }


}
