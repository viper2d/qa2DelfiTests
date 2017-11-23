package WeatherStepDefinitions;

import WeatherStepDefinitions.model.WeatherResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class WeatherRequester {

    private String requestPrefix = "http://samples.openweathermap.org/data/2.5/weather?q=";
    private String requestPostfix = "&appid=b1b15e88fa797225412429c1c50c122a1";

    public WeatherResponse getWeather(String cityName) throws IOException {

        String reqURL = requestPrefix + cityName + requestPostfix;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(reqURL, String.class);

        String jsonResponse = response.getBody();

        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponse weatherResponse = objectMapper.readValue(jsonResponse, WeatherResponse.class);

        return weatherResponse;
    }
}
