Feature: Booking journey

  Scenario: Verify that a journey is returned
    Given I navigate on "https://www.hotwire.com/" website
    And I select all travel methods
    And I enter flight from "SFO" to "LAX"
    And I select as a departing date the next day and returning after 20 days
    When I click find deal button
    Then I expect there is at least one result returned