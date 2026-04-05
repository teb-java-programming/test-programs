Feature: Basic operations

  Scenario: Trim a string input
    Given I have a string "  Edinburgh   "
    When I convert it to "trim"
    Then the string should be "Edinburgh"

  Scenario Outline: Perform random operations on string input
    Given I have a string "<word>"
    When I convert it to "<action>"
    Then the string should be "<updatedWord>"

    Examples:
      | word      | action     | updatedWord |
      | liverpool | uppercase  | LIVERPOOL   |
      | newcastle | capitalise | Newcastle   |
      | LONDON    | lowercase  | london      |

  Scenario: Fails when string input is null
    Given I have a string "null"
    Then an error should occur

  Scenario Outline: Perform arithmetic operations
    Given I have numbers <num1> and <num2>
    When I perform "<operation>"
    Then the numeric result should be <result>

    Examples:
      | num1 | num2 | operation | result |
      | 5    | 3    | add       | 8      |
      | 10   | 4    | subtract  | 6      |
      | 5    | 9    | subtract  | -4     |
      | 6    | 2    | multiply  | 12     |
      | 7    | 0    | multiply  | 0      |
      | 10   | 2    | divide    | 5      |

  Scenario: Division by zero
    Given I have numbers 10 and 0
    When I perform "divide"
    Then an error should occur

  Scenario: Check true value
    Given I have a boolean value true
    Then the boolean should be true

  Scenario: Check false value
    Given I have a boolean value false
    Then the boolean should be false
