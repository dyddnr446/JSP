package Yonguk.Yonguk;

import tools.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class WebApiExRateProvider {
    BigDecimal getKRWExeRate(String currency) throws IOException {
        URL url = new URL("https://open.er-api.com/v6/latest/USD"+ currency);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String response = br.lines().collect(Collectors.joining());
        System.out.println(response);
        ObjectMapper mapper = new ObjectMapper();
        ExRateData data = mapper.readValue(response, ExRateData.class);
        System.out.println(data);

        return data.rates().get("KRW");
    }
}
