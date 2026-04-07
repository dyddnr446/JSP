package Yonguk.Yonguk.Payment;

import Yonguk.Yonguk.exrate.ExRateData;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Component
public class WebApiExRateProvider implements ExRateProvider {
    @Override
    public BigDecimal getExeRate(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/USD"+ currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String response = br.lines().collect(Collectors.joining());
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        System.out.println(data);
        System.out.println("API ExRate: "+ data.rates().get("KRW"));
        return data.rates().get("KRW");
    }
}
