Feature:
  Verify different Rest API request operations

  Scenario: Verify the author of post id 2
    Given I perform GET operation for "/posts/{id}"
      |id|
      |2 |
    Then I should see the author name as "MikeN"

  Scenario: Verify Post operation for Profile
    Given I perform POST operation for "/posts/{profileNo}/profile", "2" with body
      |name|role|
      |Mike|QA  |
    Then I should see the body has name as "Mike"

  Scenario: Verify DELETE operation after POST
    Given I perform POST operation for "/posts/", with body
      |id |title      |author|
      |5  |Delete Test|MJN   |
    And I perform DELETE operation for "/posts/{id}"
      |id|
      |5 |
    And I perform GET operation for "/posts/{id}"
      |id|
      |5 |
    Then I should not see the body with title as "Delete Test"