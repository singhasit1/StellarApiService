Feature: Area Service API Testing

  @Sanity
  Scenario: Create an Area
    Given The Apis "/areas" up and running for area service
    When  User Create A New Area Named As "North England" and Area Code As "SE"
    When  Perform the POST request
    Then  Verfiy That POST Response Code Should Be 200
    And   Verify That A New Data Created As "North England"
    And   Verfiy That Header Content Type Is "application/json;charset=UTF-8"

  @Sanity
  Scenario: Fetch Area details
    Given The Apis "/areas" up and running for area service
    When  Perform the GET request
    Then  The response code should be 200
    And   User should see the following response
      | Fields          |Values|
      |area_name        |North England|
      |area_code        |SE|
      |audience_set_key |1|
    And Verfiy That Header Content Type Is "application/json;charset=UTF-8"


  @Sanity
  Scenario: Amend Area Name and Description
    Given The Apis "/areas/228" up and running for area service
    When  User Specify The Area Key 228 For Change The Area Name "North England" To "South England"
    And   Perform the PUT request
    Then  Verfiy That PUT Response Code Should Be 500
    And   Verify That Area Name "North England" Should Replace To "South England"
    And   Verfiy That Header Content Type Is "application/json;charset=UTF-8"

  @Sanity
  Scenario: Delete an Area
    Given The Apis "/areas/228" up and running for area service
    When  User Delete An Area As "South England"
    When  Perform the DELETE request
    And   Verify That Area Name "South England" Deleted From Area List
    And   Verfiy That Header Content Type Is "application/json;charset=UTF-8"



