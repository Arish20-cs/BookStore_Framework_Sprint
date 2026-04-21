package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookSteps {

    Response response;
    String requestBody;

    String baseUrl = "https://bookstore.toolsqa.com";

   
    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyTmFtZSI6IkJvb2tTdG9yZTg0ODkwNCIsInBhc3N3b3JkIjoiUGFzc3dvcmQjMSIsImlhdCI6MTc3NjczODkzOH0.4yBHtNXdVNU36AvM-k4q_ma4ZOzr7VAfEkNU0Xan9js";
    String userId = "2e518571-3975-4527-9e08-1bac56f25048";

    String isbn;
    String secondIsbn;


    @Given("The user API base URL is set")
    public void the_user_api_base_url_is_set() {
        RestAssured.baseURI = baseUrl;
    }

    @Given("the book store endpoint is available")
    public void the_book_store_endpoint_is_available() {
        
    }

  

    public void fetchISBNs() {
        Response res = given()
                .when()
                .get("/BookStore/v1/Books");

        isbn = res.jsonPath().getString("books[0].isbn");
        secondIsbn = res.jsonPath().getString("books[1].isbn");
    }


    // Fetch all books

    @When("I send a GET request to fetch all books")
    public void getBooks() {
        response = given()
                .when()
                .get("/BookStore/v1/Books");
    }


    // Retrieve book with valid ISBN

    @Given("a valid ISBN is available")
    public void a_valid_isbn_is_available() {
        fetchISBNs();
    }

    @When("I send a GET request to retrieve the book")
    public void i_send_a_get_request_to_retrieve_the_book() {
        response = given()
                .queryParam("ISBN", isbn)
                .when()
                .get("/BookStore/v1/Book");
    }


    // Add book with valid ISBN and token

    @Given("valid token and valid ISBN are available")
    public void valid_token_and_valid_isbn_are_available() {
        fetchISBNs();
    }

    @When("I send a POST request to add a book")
    public void addBook() {
        requestBody = "{ \"userId\": \"" + userId + "\"," +
                "\"collectionOfIsbns\": [{ \"isbn\": \"" + isbn + "\" }] }";

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .post("/BookStore/v1/Books");
    }

  
    // Replace existing book

    @Given("valid token userId and book details are available")
    public void valid_token_user_id_and_book_details_are_available() {
        fetchISBNs();
        addBook();
        fetchISBNs();
    }

    @When("I send a PUT request to replace the book")
    public void i_send_a_put_request_to_replace_the_book() {
        requestBody = "{ \"userId\": \"" + userId + "\"," +
                "\"isbn\": \"" + secondIsbn + "\" }";

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .put("/BookStore/v1/Books/" + isbn);
    }

   
    // Delete book with proper authorization

    @Given("valid token userId and ISBN are available")
    public void valid_token_user_id_and_isbn_are_available() {
        fetchISBNs();
        addBook();
    }

    @When("I send a DELETE request to delete the book")
    public void i_send_a_delete_request_to_delete_the_book() {
        requestBody = "{ \"isbn\": \"" + isbn + "\", \"userId\": \"" + userId + "\" }";

        response = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(requestBody)
                .when()
                .delete("/BookStore/v1/Book");
    }


    // Verify user collection after deletion-related flow

    @Given("valid token userId and valid ISBN are available")
    public void valid_token_user_id_and_valid_isbn_are_available() {
        fetchISBNs();
        addBook();
    }

    @When("I send a GET request to retrieve the user book collection")
    public void i_send_a_get_request_to_retrieve_the_user_book_collection() {
        response = given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/Account/v1/User/" + userId);
    }

    @Then("the response body should not contain the deleted book ISBN")
    public void the_response_body_should_not_contain_the_deleted_book_isbn() {
        String responseText = response.asString();
        assertFalse(responseText.contains(isbn));
    }

    // COMMON VALIDATION

    @Then("the response status should be {int}")
    public void validateStatusCode(Integer expectedStatusCode) {
        System.out.println("Response Body: " + response.asPrettyString());
        System.out.println("Actual Status Code: " + response.getStatusCode());
        assertEquals(response.getStatusCode(), expectedStatusCode.intValue());
    }
}