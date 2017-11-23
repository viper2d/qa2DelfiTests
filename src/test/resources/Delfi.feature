Feature: Testing Delfi test

  Background:
    Given Client attributes are:
      | name         | Dimko        |
      | personalCode | 111111-11111 |
      | phone        | 02           |

  
  Scenario: First scenario
    Given Print test anotation Hello World!
    And the weather is sunny with a temperature 7
    When we are sending data to server
    Then Temperatures are:
    | 22.2 |
    | 22.5 |

    #Scenario outline
      #Given blabla <city>
        #Examples:
        #| city   |
        #| Alaska |


