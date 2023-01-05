#Author: manohar
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)



  #   @ignore
  #   Scenario: Verify new place being added sucessfully using addPlace API
  #    Given Add Place Payload
  #    When user calls "addPlaceAPI" with post http request
  #    Then API call got sucess with statuscode as 200
  #    And "status" in response body is "OK"
  #    And "scope" in responseee body is "APP"
  
Feature: Validating place API`s  
	@AddPlace
  Scenario Outline: Verify new place being added sucessfully using addPlace API
    Given Add Place Payload with "<Address>" "<Language>" "<name>"
    When user calls "AddPlaceAPI" with "post" http request
    Then API call got sucess with statuscode as 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify created place_id maps to "<name>" using "getPlaceAPI"

    Examples: 
      | Address                 | Language  | name            |
      | 29 side layout cohen 09 | French-IN | Frontline house |

	@DeletePlace
  Scenario: Verify delete palce api works sucessfully
    Given DeletePlaceAPI payload
    When user calls "deletePlaceAPI" with "post" http request
    Then API call got sucess with statuscode as 200
    And "status" in response body is "OK"
