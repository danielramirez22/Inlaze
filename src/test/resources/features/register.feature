Feature: Register - Register a new user

  Background:
    Given The user enters into the page

  Scenario Outline: Register user in Inlaze
    When The user fills the form
      |<fullName>   | <email>               | <password>  | <RepeatPassword> |
    And Click on sign up button
    Then Verify account creation
    Examples:
      | fullName       | email                | password  | RepeatPassword |
      | Daniel Ramirez | danieltest@gmail.com | Test2025* | Test2025*      |


  Scenario Outline: Validate fullname field
    When The user fills the form
      |<fullName>   | <email>               | <password>  | <RepeatPassword> |
    Then Validate number of words
    Examples:
      |fullName   | email                 | password    | RepeatPassword  |
      |Daniel     | danieltest@gmail.com  | Test2025*   | Test2025*       |

  Scenario Outline: Validate email is not registered
    When The user fills the form
      |<fullName>   | <email>               | <password>  | <RepeatPassword> |
    And Click on sign up button
    Then Validate the account does not be created
    Examples:
      | fullName | email                | password  | RepeatPassword |
      | Daniel Ramirez | danieltest@gmail.com | Test2025* | Test2025*      |


  Scenario Outline: Email structure is not correct on the field
    When The user fills the form
      |<fullName>   | <email>               | <password>  | <RepeatPassword> |
    Then Verify if sign up button is not enabled for email
    Examples:
      | fullName    | email           | password  | RepeatPassword |
      | Daniel Test | danieltestgmail | Test2025* | Test2025*      |

  Scenario Outline: Password and password repeat match
    When The user fills the password fields
      | <password> | <RepeatPassword> |
    Then Validate if the passwords match
      | <password> | <RepeatPassword> |
    Examples:
      | password  | RepeatPassword |
      | Test2025* | Test2025*      |
      | Test2025* | Test2025       |


  Scenario Outline: Evaluate if the form is completed without a field
    When The user fills the form
      |<fullName>   | <email>               | <password>  | <RepeatPassword> |
    Then Verify if sign up button is not enabled
    Examples:
      | fullName    | email                | password  | RepeatPassword |
      | Daniel Test |                      | Test2025* | Test2025*      |
      | Daniel Test | danieltest@gmail.com | Test2025* | Test2025*      |

  Scenario Outline: Password and password repeat match
    When The user fills the form
      |<fullName>   | <email>               | <password>  | <RepeatPassword> |
    Then Validate if the passwords follow rules
      | <password> | <RepeatPassword> |
    Examples:
      | fullName    | email                | password  | RepeatPassword |
      | Daniel Test | danieltest@gmail.com | H1n3er    | H1n3er         |
      | Daniel Test | danieltest@gmail.com | Test2025* | Test2025*      |