Feature: Google Search

  Scenario: Google Search
    Given I open the Google homepage
    And I accept the consent dialog
    When I search for "Selenium WebDriver"
    Then I should see search results containing "Selenium" and "WebDriver"