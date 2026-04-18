package utils;

import config.ConfigReader;
import context.ScenarioContext;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {

    public static RequestSpecification getRequest() {

        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.setBaseUri(ConfigReader.get("baseUrl"));
        builder.setContentType("application/json");

        Object token = ScenarioContext.get("token");

        if (token != null) {
            builder.addHeader("Authorization", "Bearer " + token);
        }

        return RestAssured.given().spec(builder.build()).log().all();
    }
}