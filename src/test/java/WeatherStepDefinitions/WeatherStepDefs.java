package WeatherStepDefinitions;

import WeatherStepDefinitions.model.WeatherResponse;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class WeatherStepDefs {

    private String cityName;
    private WeatherRequester wr = new WeatherRequester();
    private WeatherResponse weatherResponse;

    @Given("city name is (.*)")
    public void city_name(String name) {

        cityName = name;
    }

    @When("requesting weather information")
    public void request_weather_info() throws IOException {

        weatherResponse = wr.getWeather(cityName);
    }

    @Then("coordinates are Lon:(.*) and Lat:(.*)")
    public void check_coordinates(BigDecimal lon, BigDecimal lat) {

        assertEquals(lon, weatherResponse.getCoord().getLon());
        assertEquals(lat, weatherResponse.getCoord().getLat());
    }
}
