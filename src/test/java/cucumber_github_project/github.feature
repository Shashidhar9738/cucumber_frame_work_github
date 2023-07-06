Feature: validating the endpoint of github by using authorization

  Scenario: creating the repo
    #given is a pre condition
    Given login to github
    # WHen is the action performed
    When create a repo "/user/repos" storing the repo name
    # Then is a ppost condition kind of verifying the result
    Then verify the status code 201

  Scenario: creating a issue
    Given creating an issue with json data
    When creating a issue "/repos/" saving owner name and repo name
    Then Verify the Status code is 200

  Scenario: deleting the created repo
    Given deleting the repo
    When deleteling a repo "/repos/" saving owner name and repo name
    Then Verify the Status code for deleting the repo 204
