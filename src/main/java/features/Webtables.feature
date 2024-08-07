@tag
Feature: Automation of http://www.way2automation.com/angularjs-protractor/webtables/

  @Teladoc_Webtables_TC1
  Scenario: Add a user and validate the user has been added to the table
    Given Verify the user navigated to webtable page
    

   @Teladoc_Webtables_TC2
  Scenario Outline: Delete the user "novak" from the table and validate the user has been deleted
    Given Verify the user navigated to webtable page
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
