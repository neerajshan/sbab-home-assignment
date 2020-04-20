Feature: Top 10 bus lines in Stockholm area with most bus stops on
  their route. can be retrieve

  Scenario: client makes call to GET /v1/bus/topbuslines
    When the client calls /v1/bus/topbuslines
    Then the client receives status code of 200
    And the client receives response src/test/resources/fixture/top10bus_api_response.json
   