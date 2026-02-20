@Regression @AdvancedXPath
Feature: Advanced XPath Selectors - Complex Scenarios
  As a QA Engineer
  I want to practice advanced XPath selectors
  So that I can master complex element selection techniques

  Background:
    Given User is on XPath practice page

  @XPathByAttribute
  Scenario: User can locate elements using XPath attribute selectors
    When User clicks button with data attribute
    Then Success message should be displayed

  @XPathByText
  Scenario: User can locate elements by partial text
    When User enters email as "xpath.test@example.com"
    And User enters password as "XPath123"
    And User clicks sign in button
    Then Success message should be displayed

  @XPathParentChild
  Scenario: User can use XPath parent-child relationships
    When User enters email as "parent.child@example.com"
    And User enters password as "ParentChild123"
    And User clicks sign in button
    Then Success message should be displayed

  @XPathFollowingSibling
  Scenario: User can use XPath following-sibling axis
    When User performs login with email "sibling@example.com" and password "Sibling123"
    Then Success message should be displayed

  @XPathPrecedingSibling
  Scenario: User can use XPath preceding-sibling axis
    When User performs login with email "preceding@example.com" and password "Preceding123"
    Then Success message should be displayed

  @XPathContains
  Scenario: User can use XPath contains() function
    When User enters email as "contains@example.com"
    And User enters password as "Contains123"
    And User clicks sign in button
    Then Success message should be displayed

  @XPathStartsWith
  Scenario: User can use XPath starts-with() function
    When User performs login with email "startswith@example.com" and password "StartsWith123"
    Then Success message should be displayed

  @XPathEndsWith
  Scenario: User can use XPath normalize-space() function
    When User performs login with email "normalize@example.com" and password "Normalize123"
    Then Success message should be displayed

