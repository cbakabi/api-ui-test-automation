Feature: Verify the yahoo finance interface works as expected

  Scenario: Verify the user is able to login and retrieve calendar values
    Given I am on the yahoo page
    And I navigate to the login page
    When I enter the username in the login page
    * I click on the Next button
    * I enter the password in the login page
    * I click on the Next button
    Then I navigate to the Finance button
    And I navigate to the calendar button from the Market Data tab
    Then I validate "Earnings" is present on the 25th of July
    Then I validate "Stock splits" is present on the 25th of July
    Then I validate "Economic events" is present on the 25th of July
    * I leave the yahoo page