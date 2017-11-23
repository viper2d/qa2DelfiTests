Feature: City coordinates test
  Scenario: Test city coordinates
    Given city name is London
    When requesting weather information
    Then coordinates are Lon:-0.13 and Lat:51.51