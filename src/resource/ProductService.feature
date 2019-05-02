Feature: Area Service API Testing

  @Sanity
  Scenario: Fetch Area details
    Given The Apis1 "/areas" up and running for area service
    When  Perform1 the GET request
    Then  The response1 code should be 200
    And   User should1 see the following response
      | Fields          |Values|
      |area_name        |South England|
      |area_code        |SE|
      |audience_set_key |1|
      |South England    |South England |
    And   Verfiy That Header Content Type Is "application/json;charset=UTF-8"

  @Sanity
  Scenario: Create an Area
    Given The Apis1 "/areas" up and running for area service
    When  User Create1 A New Area Named As "South England" and Area Code As "SE"
    When  Perform1 the POST request
    Then  Verfiy1 That POST Response Code Should Be 200
    And   Verify1 That A New Data Created As "South England"
    And   Verfiy1 That Header Content Type Is "application/json;charset=UTF-8"


