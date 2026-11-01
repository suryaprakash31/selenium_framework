Feature: Login

  Scenario: Valid login opens the dashboard
    Given the user is on the login page
    When the user logs in with username "Admin" and password "admin123"
    Then the dashboard heading should be "Dashboard"