Feature:
  Verify different JSON handling methods

  Scenario: Deserialize JSON string by org.json
    Given I have JSON in string format
    When I deserialize the JSON into Array
    Then I should see index 0 name is "Java"
    And I should see JSON array length is 3

  Scenario: Deserialize JSON file by org.json
    Given I have a JSON from a file converted into Input Stream
    Then I should see "pageName" is "Homepage"

  @jackson
  Scenario: Deserialize JSON file by Jackson API
    Given I have a JSON from a file converted into POJO "myFile.json"
    Then I should see "AGe" is "30" by using Jackson Mapper

  Scenario: Deserialize Complex JSON file by Jackson API
    Given I have a JSON from a file converted into POJO "location.json"
    Then Verify values from JSON Array