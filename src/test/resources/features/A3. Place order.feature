Feature: Customer successfully place a order.
  Customer must login to place an order.
  Customer: Mahbubur Rahman

  Scenario: login to website as Mahbubur Rahman
    Given I am at home page
    And I have login with valid credentials in my account
    Then Verify I have login into my account successfully

  Scenario: Clear my shopping cart
    And Clear my shopping cart

  Scenario: Mahbubur add product in his cart
    When I have added product(s) in my cart page
    And Verify all added product(s) name and nett price showing in my cart page

  Rule: Order should be place in erp
    Scenario: Mahbubur successfully place an order
      When I have click checkout button in my cart page
      And I have click next button at delivery section in checkout page
      Then I have input customer purchase order number at payment section in checkout page
      And I have click confirm button at payment section in checkout page
      Then Verify my order place successfully
      And Verify my order place in erp