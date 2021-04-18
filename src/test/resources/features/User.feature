Feature: User

  # POST - SUCCESSFULL
  Scenario: User Registration Successfull
    Given User provides following information for registration
      | email              | password |
      | eve.holt@reqres.in | pistol   |
    Then User should be created successfully

  # POST - UNSUCCESSFULL
  Scenario: User Registration Unsuccessfull
    Given User provides following information for registration
      | email              | password |
      | eve.holt@reqres.in |          |
    Then User should be not created with message 'Missing password'

  # GET - SUCCESSFULL
  Scenario: User Found
    Given User has the Id 2
    Then User information should be available with below details
      | id | email                  | first_name | last_name | avatar                                  |
      | 2  | janet.weaver@reqres.in | Janet      | Weaver    | https://reqres.in/img/faces/2-image.jpg |

  # GET - UNSUCCESSFULL
  Scenario: User Not Found
    Given User has the Id 12345666
    Then User information should not be available

  # DELETE - SUCCESSFULL
  Scenario: User Delete Successfull
    Then User having Id 2 should be deleted successfully

  # DELETE - UNSUCCESSFULL
  Scenario: User Delete Unsuccessfull
    Then User having no Id should not be deleted