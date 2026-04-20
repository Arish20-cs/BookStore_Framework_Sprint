#Author : Ganesh , Joseph , Sabarinathan
#Created On : 20-04-2026
#Module : Book


Feature: Book Store API

Background:
Given The user API base URL is set

@BookStoreWebAPI12TC_18
Scenario: Fetch all books and verify status code
Given the book store endpoint is available
When I send a GET request to fetch all books
Then the response status should be 200

@BookStoreWebAPI13TC_19
Scenario: Verify retrieval of book using valid ISBN
Given a valid ISBN is available
When I send a GET request to retrieve the book
Then the response status should be 200

@BookStoreWebAPI01TC_20
Scenario: Validate error response for invalid ISBN
Given invalid ISBN is prepared
When I send a GET request to fetch book by ISBN
Then the response status should be 400


@BookStoreWebAPI15TC_21
Scenario: Verify adding book with valid ISBN and token
Given valid token and valid ISBN are available
When I send a POST request to add a book
Then the response status should be 201

@BookStoreWebAPI01TC_22
Scenario: Validate adding multiple books
Given valid token and multiple ISBNs are available
When I send a POST request to add multiple books
Then the response status should be 201


@BookStoreWebAPI01TC_23
Scenario: Verify adding book with invalid ISBN
Given valid token and invalid ISBN are available
When I send a POST request to add a book
Then the response status should be 400

@BookStoreWebAPI18TC_24
Scenario: Validate replacement of existing book
Given valid token userId and book details are available
When I send a PUT request to replace the book
Then the response status should be 200

@BookStoreWebAPI01TC_25
Scenario: Validate replacement of same book using an existing ISBN
Given valid token userId and same ISBN are available
When I send a PUT request to replace book
Then the response status should be 400


@BookStoreWebAPI01TC_26
Scenario: Verify updating book with invalid ISBN
Given valid token userId and invalid ISBN are available for update
When I send a PUT request to replace book
Then the response status should be 400

@BookStoreWebAPI01TC_27
Scenario: Verify updating book with invalid token
Given invalid token with valid userId and ISBN are available
When I send a PUT request to replace book
Then the response status should be 401


@BookStoreWebAPI21TC_28
Scenario: Validate delete request with proper authorization
Given valid token userId and ISBN are available
When I send a DELETE request to delete the book
Then the response status should be 204

@BookStoreWebAPI21TC_29
Scenario: Verify deletion of book with valid isbn and token
Given valid token userId and valid ISBN are available
When I send a GET request to retrieve the user book collection
Then the response status should be 200
And the response body should not contain the deleted book ISBN

@BookStoreWebAPI01TC_30
Scenario: Validate deleting books with invalid ISBN
Given valid token userId and invalid ISBN are available for delete
When I send a DELETE request to remove book
Then the response status should be 400

@BookStoreWebAPI01TC_31
Scenario: Validate delete request when done multiple times
Given valid token userId and ISBN are available
When I send a DELETE request to remove book
And I send a DELETE request to remove book again
Then the response status should be 400

@BookStoreWebAPI01TC_32
Scenario: Validate deleting all books without authentication token
Given valid userId without token is available
When I send a DELETE request to remove all books
Then the response status should be 401

@BookStoreWebAPI01TC_33
Scenario: Validate deleting all books with authentication token
Given valid token and userId are available
When I send a DELETE request to remove all books
Then the response status should be 204