Feature: A list of all unique bus numbers can be retrieve

  Scenario: client makes call to GET /v1/bus/
    When the client calls /v1/bus/
    Then the client receives status code of 200
    And the client receives response src/test/resources/fixture/all_bus_feature_response.json
   