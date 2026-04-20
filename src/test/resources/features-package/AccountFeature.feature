#Author:ARISH,VEERESHWAR
#Created On:20-04-2026
#Module:Account

Feature:Account 
Background:
		 Given The user API base URL is set
		 
# ---------------- USER ----------------

@BookStoreWebAPI01TC_01
Scenario: Verify user can create account with valid details
Given valid user payload is prepared
When I send a POST request to create a user
Then the response status should be 201
@BookStoreWebAPI01TC_02
Scenario: Verify user creation with existing username/password
Given duplicate user payload is prepared
When I send a POST request to create a user
Then the response status should be 406
@BookStoreWebAPI01TC_03
Scenario: Create user with extra JSON body
Given user payload with extra fields is prepared
When I send a POST request to create a user
Then the response status should be 400
@BookStoreWebAPI01TC_04
Scenario: Create user with empty fields
Given empty user payload is prepared
When I send a POST request to create a user
Then the response status should be 400

# ---------------- TOKEN ----------------
@BookStoreWebAPI01TC_05
Scenario: Generate token with valid username & password
Given valid credentials are prepared
When I send a POST request to generate token
Then the response status should be 200
@BookStoreWebAPI01TC_06
Scenario: Generate token with invalid username
Given invalid username is prepared
When I send a POST request to generate token
Then the response status should be 401
@BookStoreWebAPI01TC_07
Scenario: Generate token with invalid password
Given invalid password is prepared
When I send a POST request to generate token
Then the response status should be 400
@BookStoreWebAPI01TC_08
Scenario: Create user with invalid JSON body
Given invalid JSON payload is prepared
When I send a POST request to create a user
Then the response status should be 400
@BookStoreWebAPI01TC_09
Scenario: Validate API error when extra field is provided
Given user payload with extra invalid field is prepared
When I send a POST request to create a user
Then the response status should be 406

# ---------------- AUTH ----------------

@BookStoreWebAPI01TC_10
Scenario: Validate authorization using valid token
Given valid token is available
When I send a POST request to authorize user
Then the response status should be 200
@BookStoreWebAPI01TC_11
Scenario: Validate authorization using invalid token
Given invalid token is prepared
When I send a POST request to authorize user
Then the response status should be 401
@BookStoreWebAPI01TC_12
Scenario: Validate authorization without token
Given no token is provided
When I send a POST request to authorize user
Then the response status should be 401

# ---------------- FETCH USER ----------------

@BookStoreWebAPI01TC_13
Scenario: Fetch user using correct UUID
Given valid user ID is available
When I send a GET request to fetch user
Then the response status should be 200
@BookStoreWebAPI01TC_14
Scenario: Fetch user with invalid UUID
Given invalid user ID is prepared
When I send a GET request to fetch user
Then the response status should be 404



# ---------------- DELETE USER ----------------

@BookStoreWebAPI01TC_15
Scenario: Delete user with valid UUID & token
Given valid user ID and token are available
When I send a DELETE request to delete user
Then the response status should be 401
@BookStoreWebAPI01TC_16
Scenario: Delete user with invalid UUID
Given invalid user ID is prepared
When I send a DELETE request to delete user
Then the response status should be 404
@BookStoreWebAPI01TC_17
Scenario: Delete user without token
Given no token is provided
When I send a DELETE request to delete user
Then the response status should be 401
