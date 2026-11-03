@login
Feature: Login

  @smoke
  Scenario: Valid login opens the dashboard
    Given the user is on the login page
    When the user logs in with username "Admin" and password "admin123"
    Then the dashboard heading should be "Dashboard"

    @regression
    Scenario Outline: Invalid login shows an error
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the user should see the error message "<message>"

    Examples:
      | username   | password       | message             |
      | Admin      | wrong_password | Invalid credentials |
      | wrong_user | admin123       | Invalid credentials |
      | wrong_user | wrong_password | Invalid credentials |