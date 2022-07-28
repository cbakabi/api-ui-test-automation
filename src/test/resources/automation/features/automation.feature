Feature: Verify account workflow functions as expected

  Scenario: Register account is successful
    Given I am a new user requesting to "register"
    Then I get a 200 status code
    And the response message contains the following headers
      | id    |
      | token |

  Scenario: Login to account is successful
    Given I am an existing user requesting to "login"
    Then I get a 200 status code
    And I receive the message containing "token"

  Scenario: List resource from api is successful
    Given I retrieve the resource
    Then I get a 200 status code
    And the response message contains the following headers
      | page          |
      | per_page      |
      | total         |
      | total_pages   |
      | data          |
      | id            |
      | name          |
      | year          |
      | color         |
      | pantone_value |

