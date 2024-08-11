Feature: Automation of http://www.way2automation.com/angularjs-protractor/webtables/

  Background: 
   Given Verify the user navigated to webtable page

  @Teladoc_Webtables_TC1
  Scenario Outline: Add a user and validate the user has been added to the table
    When Adding a user <firstname>,<lastname>,<role>,<email>
    Then Verify user is added to the table
    Then Close the browser

    Examples: 
      | firstname | lastname | role  | email                    |
      | Yashik    | Narula   | admin | yashiknarula89@gmail.com |

  @Teladoc_Webtables_TC2
  Scenario Outline: Delete the user "novak" from the table and validate the user has been deleted
    When Delete the user <name>
    Then Verify user is deleted from the table
    Then Close the browser

    Examples: 
      | name  |
      | novak |
