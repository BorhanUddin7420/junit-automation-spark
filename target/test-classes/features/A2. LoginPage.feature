#Author: Md. Borhan Uddin Sarker<borhan@bs-23.net>
#Keywords Summary : Admin - Login
# Date: 29 Dec 2020

Feature: Admin successfully login

  Scenario: I am able to login as admin
    Given I am at home page
    When I click login button from home page
    Then I input "developer@comalytics.comt" as email at login page
    And I input "P@ssme2344" as password at login page
    And I click login button at login page
    Then verify I login successfully and adminamtration menu showing

