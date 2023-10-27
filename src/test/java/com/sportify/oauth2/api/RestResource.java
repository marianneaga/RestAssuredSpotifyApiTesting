package com.sportify.oauth2.api;

import io.restassured.response.Response;

import java.util.Map;

import static com.sportify.oauth2.api.SpecBuilder.*;
import static io.restassured.RestAssured.given;

public class RestResource {

    public static Response post(String path, String accessToken, Object requestPlaylist) {
        return given(getRequestSpecification())
                .body(requestPlaylist)
                .auth().oauth2(accessToken)
                .when()
                .post(path)
                .then().spec(getResponseSpecification())
                .extract()
                .response();
    }

    public static Response get(String path, String accessToken) {
        return given(getRequestSpecification())
                .auth().oauth2(accessToken)
                .when()
                .get(path)
                .then().spec(getResponseSpecification())
                .extract()
                .response();
    }

    public static Response put(String path, String accessToken, Object requestPlaylist) {
        return given(getRequestSpecification())
                .auth().oauth2(accessToken)
                .body(requestPlaylist)
                .when()
                .put(path)
                .then().spec(getResponseSpecification())
                .extract()
                .response();
    }

    public static Response postAccount(Map<String, String> formParams) {
        return given(getAccountRequestSpecification())
                .formParams(formParams)
                .when()
                .post(Route.API + Route.TOKEN)
                .then()
                .spec(getResponseSpecification())
                .extract()
                .response();

    }
}
