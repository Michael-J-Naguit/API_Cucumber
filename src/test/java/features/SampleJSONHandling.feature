Feature:
  Verify different JSON handling methods

  Scenario: Deserialize JSON string by org.json
  Given I have JSON in string format
  When I deserialize the JSON into Array
  Then I should see index 0 name is "Java"

  Scenario: Deserialize JSON string by Jackson ObjectMapper