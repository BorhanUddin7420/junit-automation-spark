#Author: Md. Borhan Uddin Sarker<borhan@bs-23.net>
#Keywords Summary : Admin - Login
# Date: 29 Dec 2020

Feature: Admin unable to login

  Scenario: I am unable to login with valid email and invalid password
    Given I am at home page
    When I click login button from home page
    Then I input "developer@comalytics.com" as email at login page
    And I input "P@ssme" as password at login page
    And I click login button at login page
    Then verify error message for login "Your Email or Password is incorrect Please try again"

  Scenario: I am unable to login with invalid email and valid login
    Given I am at home page
    When I click login button from home page
    Then I input "developer@comalytics.comt" as email at login page
    And I input "P@ssme2344" as password at login page
    And I click login button at login page
    Then verify error message for login "Your Email or Password is incorrect Please try again"

#  Scenario: I go to adminamtration section
#    When I click adminamtration button
#    Then verify I successfully navigated to admin dashboard
