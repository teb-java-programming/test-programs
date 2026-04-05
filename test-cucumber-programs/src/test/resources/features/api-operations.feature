Feature: API operations

  Scenario Outline: Transform input string via API
    Given I send a request with "<input>"
    When I call the API
    Then the response should be <boolean>

    Examples:
      | input | boolean |
      | Test  | false   |
      | 1234  | true    |
      | null  | false   |