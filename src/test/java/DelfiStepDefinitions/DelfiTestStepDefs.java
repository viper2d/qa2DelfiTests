package DelfiStepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class DelfiTestStepDefs {
    @Given("Print test anotation (.*)")
    public void print_test_annotation(String annotation) {
        System.out.println(annotation);
    }

    @Given("the weather is (.*) with a temperature ([0-9*])")
    public void weather_data_is(String weather, BigDecimal temperature) {

    }

    @Given("Client attributes are:")
    public void client_attributed_are(Map<String, String> params) {
        System.out.println(params);
    }

    @When("we are sending data to server")
    public void sending_data_to_server() {

    }

    @Then("Temperatures are:")
    public void check_temperatures(List<BigDecimal> temperatures) {
        System.out.println(temperatures);
    }


}