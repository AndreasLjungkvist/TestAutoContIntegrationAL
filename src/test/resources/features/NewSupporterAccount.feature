Feature: NewSupporterAccount

  Background: As a supporter, I want to create a new membership account,
              so I can support the team and get news updates.

  Scenario Outline:
    Given I have the the web address and using a "<browser>"
    When I start the web page
    And select a date
    And fill in my "<first>" name
    And fill in my "<surname>"
    And fill in my email
    And confirm my email
    And put in a password
    And retype the password "<accordingly>"
    And "<check>" the terms and conditions
    And check off being a legal age
    And check off having read the code of conduct
    And click confirm
    Then I should join the site "<successfully>"

    Examples:
      | browser | first | surname   | accordingly | check | successfully |
      | Chrome  | Johan | Eriksson  | correctly   | yes   | yes          |
      | Firefox | Erik  | Johansson | correctly   | yes   | yes          |
      | Chrome  | Erik  |           | correctly   | yes   | no           |
      | Chrome  | Erik  | Johansson | incorrectly | yes   | no           |
      | Chrome  | Erik  | Johansson | correctly   | no    | no           |
    