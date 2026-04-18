package utils;

import io.restassured.response.Response;

public class ResponseValidator {

    public static void validateStatusCode(Response response, String expected) {

        if (expected.contains("200"))
            response.then().statusCode(200);

        else if (expected.contains("201"))
            response.then().statusCode(201);

        else if (expected.contains("204"))
            response.then().statusCode(204);

        else if (expected.contains("400"))
            response.then().statusCode(400);

        else if (expected.contains("401"))
            response.then().statusCode(401);

        else if (expected.contains("404"))
            response.then().statusCode(404);
    }

    public static void validateNotNull(Response response, String key) {
        response.then().assertThat().body(key, org.hamcrest.Matchers.notNullValue());
    }

    public static void validateEquals(Response response, String key, String value) {
        response.then().assertThat().body(key, org.hamcrest.Matchers.equalTo(value));
    }
}