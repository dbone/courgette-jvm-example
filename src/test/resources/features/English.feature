@english @regression
Feature: English

  Scenario: Ensure that stack overflow question page 1 can be opened
    When I navigate to Stack Overflow question page 1
    Then I verify Stack Overflow question page 1 is opened


  Scenario: Ensure that stack overflow question page 2 can be opened
    When I navigate to Stack Overflow question page 2
    Then I verify Stack Overflow question page 2 is opened