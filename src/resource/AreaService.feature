Feature: Area Service API

  @Sanity
  Scenario: Fetch Area details
    Given The Apis "/areas" up and running for area service
    When  Perform the GET request
    Then  The response code should be 200
    And   User should see the following response
      | Fields          |Values|
      |area_name        |Network|
      |area_code        |NET|
      |audience_set_key |1|
    And Verfiy That Header Content Type Is "application/json;charset=UTF-8"

  @Sanity
  Scenario: Create an Area
    Given The Apis "/areas" up and running for area service
    When  User Create A New Area As "South England"
    When  Perform the POST request
    Then  Verfiy That POST Response Code Should Be 200
    And   Verify That A New Data Created As "South England"
    And   Verfiy That Header Content Type Is "application/json;charset=UTF-8"



  Scenario: Delete an Area
    Given The Apis "/areas/133" up and running for area service
    When  User Delete An Area As "South England"
    When  Perform the DELETE request
    Then  Verfiy That POST Response Code Should Be 200
    And   Verify That Area Name "South England" Deleted From Area List
    And   Verfiy That Header Content Type Is "application/json;charset=UTF-8"



