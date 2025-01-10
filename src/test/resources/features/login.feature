Feature: Login - Validate successfully login

  Background:
    Given The user enters into the page login

  Scenario Outline: Sign in Inlaze
    When Fills the email and password fields
      | <email>               | <password>  |
    And Click on sign in button
    Then Verify login is successful
    Examples:
      | email                 |  password   |
      | test@gmail.com        |  Test2025*  |

  Scenario Outline: Sign in Inlaze
    When Fills the email and password fields
      | <email>               | <password>  |
    Then Verify if sign in button is not enabled
    Examples:
      | email                | password  |
      | danieltest@gmail.com | Test2025* |
      | null                 | Test2025* |
      | danieltest@gmail.com | null      |


  Scenario Outline: Sign in Inlaze
    When Fills the email and password fields
      | <email>               | <password>  |
    And Click on sign in button
    And Verify login is successful
    And Click on profile image and logout
    Then Verify if log out was completed
    Examples:
      | email                 |  password   |
      | test@gmail.com        |  Test2025*  |