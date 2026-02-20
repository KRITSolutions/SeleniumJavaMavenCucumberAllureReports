@Smoke @XPathPractice
Feature: XPath Practice Page - Login and Form Interactions
  As a user
  I want to test the XPath practice page functionality
  So that I can practice and validate XPath selectors

  Background:
    Given User is on XPath practice page

  @Smoke @PositiveTest
  Scenario: User can successfully login with valid credentials
    When User performs login with email "practice@example.com" and password "Test@123"
    Then Login should be successful
    And Success message should be displayed

  @Smoke @PositiveTest
  Scenario: User can enter email and password separately
    When User enters email as "testuser@example.com"
    And User enters password as "password123"
    And User clicks sign in button
    Then Success message should be displayed

  @Regression @NegativeTest
  Scenario: User should see error with invalid credentials
    When User performs login with email "invalid@example.com" and password "wrongpassword"
    Then Error message should be displayed

  @Regression @FormTest
  Scenario: User can interact with checkbox element
    When User clicks checkbox element
    Then User should see "success" message

  @Regression @FormTest
  Scenario: User can interact with radio button
    When User clicks radio button
    Then User should see "success" message

  @Regression @FormTest
  Scenario: User can select option from dropdown
    When User selects "USA" from dropdown
    Then User should see "success" message

  @Regression @FormTest
  Scenario: User can enter email with placeholder attribute
    When User enters email with placeholder as "testuser@gmail.com"
    And User clicks sign in button
    Then Success message should be displayed

  @Smoke @ElementTest
  Scenario: Table element should be visible on the page
    Then Table element should be visible

  @Smoke @ElementTest
  Scenario: Dynamic element should be visible on the page
    Then Dynamic element should be visible

