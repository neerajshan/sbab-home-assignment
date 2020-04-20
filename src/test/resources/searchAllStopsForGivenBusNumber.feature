Feature: This api should return a list of every bus stop of the bus line
  provided.

  Scenario: client makes call to GET /v1/bus/{busnumber}
    When the client calls /v1/bus/626
    Then the client receives status code of 200
    And the client receives response src/test/resources/fixture/busno_626_all_stops_response.json
