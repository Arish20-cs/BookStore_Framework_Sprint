#Author : Ganesh
#Created On : 20-04-2026
#Module : Book


Feature: Book Store API

Background:
Given The user API base URL is set

@BookStoreWebAPI01TC_23
Scenario: Verify adding book with invalid ISBN
Given valid token and invalid ISBN are available
When I send a POST request to add a book
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